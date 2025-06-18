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
import model.Client;
import model.Commande;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("pharmaciePU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        CommandeDAO commandeDAO = new CommandeDAO(em);

        try {
            //  Récupération du client connecté via la session (a adapter selon le système d’authentification)
            Client client = (Client) request.getSession().getAttribute("client");
            if (client == null) {
                request.setAttribute("error", "Veuillez vous connecter avant de passer une commande.");
                request.getRequestDispatcher("connexion.jsp").forward(request, response);
                return;
            }

            //  Récupération des données du formulaire
            String adresse = request.getParameter("adresse");
            String modeRetrait = request.getParameter("mode_retrait"); // "livraison" ou "ramassage"
            String modePaiement = request.getParameter("mode_paiement"); // "comptant" ou "carte"

            //  Création de l’objet Commande
            Commande commande = new Commande();
            commande.setClient(client);
            commande.setAdresse(adresse);
            commande.setDateCommande(LocalDateTime.now());
            commande.setEtat(true); // ou false selon si la commande est confirmée ou en attente

            //  Conversion du mode de paiement et retrait en booléen
            commande.setModePaiement("carte".equalsIgnoreCase(modePaiement));
            commande.setModeRetrait("livraison".equalsIgnoreCase(modeRetrait));

            // (Optionnel) Gestion de id_paiement (null ou généré ailleurs)
            commande.setIdPaiement(null);

            //  Sauvegarde de la commande
            boolean success = commandeDAO.save(commande);

            if (success) {
                request.setAttribute("message", "Votre commande a été passée avec succès !");
                request.getRequestDispatcher("confirmation.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Une erreur s'est produite lors de l'enregistrement de la commande.");
                request.getRequestDispatcher("commande.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur serveur : " + e.getMessage());
            request.getRequestDispatcher("commande.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
