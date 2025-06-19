package controller;

import dao.MedicamentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Medicament;
import model.Panier;

import java.io.IOException;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int idMedicament = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        MedicamentDAO dao = (MedicamentDAO) getServletContext().getAttribute("medicamentDAO");
        Medicament med = dao.trouverParId(idMedicament);

        switch (action) {
            case "ajouter":
                panier.ajouterMedicament(med, 1);
                break;
            case "supprimer":
                // Implémentez la suppression si nécessaire
                break;
        }

        response.sendRedirect("catalogue");
    }
}