package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Medicament;
import model.Panier;
import service.MedicamentService;


import java.io.IOException;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {

    private MedicamentService service;

    @Override
    public void init() throws ServletException {
        service = new MedicamentService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action       = request.getParameter("action");
        int    idMedicament = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        Medicament med = service.findById(idMedicament);
        if (med == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Médicament introuvable: id=" + idMedicament);
            return;
        }

        switch (action) {
            case "ajouter":
                panier.ajouterMedicament(med, 1);
                break;
            case "supprimer":
                panier.enleverMedicament(med, 1);
                break;
            case "supprimerTout":
                panier.supprimerMedicament(med);
                break;
            case "vider":
                panier.clear();
                break;
            default:
                break;
        }

        response.sendRedirect(request.getContextPath() + "/catalogue");
    }
}
