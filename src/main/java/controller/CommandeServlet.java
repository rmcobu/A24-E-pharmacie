package controller;

import dao.CommandeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Client;
import model.Commande;
import model.Panier;
import service.CommandeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    private CommandeService commandeService = new CommandeService();

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("connexion.jsp");
            return;
        }

        // Récupère le panier en session (ou un panier vide si aucun)
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            req.getSession().setAttribute("panier", panier);
        }
        // On le met en attribut de requête pour commander.jsp
        req.setAttribute("panier", panier);
        req.getRequestDispatcher("commande.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Panier panier = (Panier) session.getAttribute("panier");
        Client client = (Client) session.getAttribute("user");

        if (client == null) {
            resp.sendRedirect("connexion.jsp");
            return;
        }

        // Récupère les champs du formulaire
        String adresse     = req.getParameter("adresse");
        boolean retrait    = "ramassage".equals(req.getParameter("modeRetrait"));
        boolean livraison = !retrait;
        boolean paiement   = "carte".equals(req.getParameter("modePaiement"));

        // Crée et persiste **seulement** l’en-tête de la commande
        Commande cmd = commandeService.creerCommande(client, adresse, retrait, paiement);
        // ───  2) Calcul des montants ────────────────────────────────────────
        BigDecimal sousTotal = BigDecimal.valueOf(panier.getTotal())
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal tps        = sousTotal.multiply(new BigDecimal("0.05"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal tvq        = sousTotal.multiply(new BigDecimal("0.09975"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total      = sousTotal.add(tps).add(tvq)
                .setScale(2, RoundingMode.HALF_UP);

        cmd.setModeRetrait(livraison);
        cmd.setSousTotal(sousTotal);
        cmd.setTps(tps);
        cmd.setTvq(tvq);
        cmd.setTotal(total);

        // Utilisez votre bibliothèque préférée (Gson, Jackson…), ici un exemple avec Gson :
        List<Map<String,Object>> itemsList = new ArrayList<>();
        panier.getItems().forEach((med, qty) -> {
            Map<String,Object> it = new HashMap<>();
            it.put("id",       med.getId());
            it.put("nom",      med.getNom());
            it.put("prix",     med.getPrix());
            it.put("quantite", qty);
            itemsList.add(it);
        });
        String itemsJson = new com.google.gson.Gson().toJson(itemsList);
        cmd.setItemsJson(itemsJson);



        // ─── 🆕 3) Persistance via JPA ────────────────────────────────────────
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("E-pharmacie");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cmd);
        em.getTransaction().commit();
        em.close();
        emf.close();

        // 5) Stocker **avant** clear() pour pouvoir l’afficher ensuite

        Panier confirmationPanier = new Panier();
        panier.getItems().forEach((med, qty) -> confirmationPanier.ajouterMedicament(med, qty));
        session.setAttribute("confirmationPanier", confirmationPanier);
        session.setAttribute("confirmationCommande", cmd);

        // 6) Vider le panier réel
        panier.clear();

        // 7) Redirection vers la page de confirmation
        resp.sendRedirect(req.getContextPath() + "/confirmation.jsp");
    }
}

