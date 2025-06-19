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

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("E-pharmacie");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Panier panier = session != null ? (Panier) session.getAttribute("panier") : null;
        req.setAttribute("panier", panier);
        req.getRequestDispatcher("commande.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("connexion.jsp");
            return;
        }

        Client client = (Client) session.getAttribute("user");
        if (client == null) {
            request.setAttribute("error", "Veuillez vous connecter avant de passer une commande.");
            request.getRequestDispatcher("connexion.jsp").forward(request, response);
            return;
        }

        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null || panier.getItems().isEmpty()) {
            request.setAttribute("error", "Votre panier est vide.");
            request.getRequestDispatcher("commande.jsp").forward(request, response);
            return;
        }

        // Récupération des champs du formulaire
        String adresse      = request.getParameter("adresse");
        String modeRetrait  = request.getParameter("mode_retrait");   // "livraison" ou "ramassage"
        String modePaiement = request.getParameter("mode_paiement");  // "carte" ou "comptant"

        // Création de l'entité Commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setAdresse(adresse);
        commande.setDateCommande(LocalDateTime.now());
        commande.setEtat(true);
        commande.setModePaiement("carte".equalsIgnoreCase(modePaiement));
        commande.setModeRetrait("livraison".equalsIgnoreCase(modeRetrait));
        commande.setIdPaiement(null); // si vous gérez un paiement ailleurs

        // Persistance de la commande
        EntityManager em = emf.createEntityManager();
        CommandeDAO commandeDAO = new CommandeDAO(em);
        boolean success;
        try {
            em.getTransaction().begin();
            success = commandeDAO.save(commande);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ServletException("Erreur lors de la sauvegarde de la commande", e);
        } finally {
            em.close();
        }

        // On vide le panier après une commande réussie
        session.removeAttribute("panier");

        if (success) {
            request.setAttribute("message", "Votre commande a été passée avec succès !");
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Une erreur s'est produite lors de l'enregistrement de la commande.");
            request.getRequestDispatcher("commande.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

