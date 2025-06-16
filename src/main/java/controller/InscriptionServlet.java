package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.ClientService;
import dao.ClientDAOImpl;
import model.Client;
import java.io.IOException;

@WebServlet("/register")
public class InscriptionServlet extends HttpServlet {
    private ClientService clientService;

    @Override
    public void init() {
        clientService = new ClientService(new ClientDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Client c = new Client();
            c.setNom(req.getParameter("nom"));
            c.setPrenom(req.getParameter("prenom"));
            c.setTelephone(req.getParameter("telephone"));
            c.setEmail(req.getParameter("email"));
            c.setPassword(req.getParameter("password"));
            c.setNumeroAssuranceMaladie(req.getParameter("numeroAssuranceMaladie"));

            clientService.register(c);
            req.setAttribute("message", "Inscription réussie. Veuillez vous connecter.");
            req.getRequestDispatcher("connexion.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Erreur lors de l'enregistrement: " + e.getMessage());
            req.getRequestDispatcher("creationCompte.jsp").forward(req, resp);
        }
    }





}
