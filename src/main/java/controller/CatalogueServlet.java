package controller;

import service.MedicamentService;
import model.Medicament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        if (filtreNom != null && !filtreNom.trim().isEmpty()) {
            liste = liste.stream()
                    .filter(m -> m.getNom().toLowerCase().contains(filtreNom.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (filtreCategorie != null && !filtreCategorie.trim().isEmpty()) {
            liste = liste.stream()
                    .filter(m -> m.getCategorie().toLowerCase().contains(filtreCategorie.toLowerCase()))
                    .collect(Collectors.toList());
        }

        request.setAttribute("medicaments", liste);
        request.getRequestDispatcher("/catalogue.jsp").forward(request, response);
    }
}