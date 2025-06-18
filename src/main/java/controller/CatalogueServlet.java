package controller;

import service.MedicamentService;
import model.Medicament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {
    private MedicamentService medicamentService;

    @Override
    public void init() throws ServletException {
        medicamentService = new MedicamentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filtreNom = request.getParameter("filtreNom");
        String filtreCategorie = request.getParameter("filtreCategorie");

        List<Medicament> liste = medicamentService.getCatalogue();

        // Filtrage
        if ((filtreNom != null && !filtreNom.isBlank()) ||
                (filtreCategorie != null && !filtreCategorie.isBlank())) {
            liste = medicamentService.filterBy(filtreNom, filtreCategorie);
        }

        request.setAttribute("medicaments", liste);
        request.getRequestDispatcher("/catalogue.jsp").forward(request, response);
    }
}