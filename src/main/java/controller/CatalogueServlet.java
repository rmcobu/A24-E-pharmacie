package controller;

import service.MedicamentService;
import model.Medicament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {
    private MedicamentService medicamentService;

    @Override
    public void init() {
        medicamentService = new MedicamentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des paramètres
        String filtreNom       = request.getParameter("filtreNom");
        String filtreCategorie = request.getParameter("filtreCategorie");
        String tri             = request.getParameter("tri");

        // Filtrage initial
        List<Medicament> medicamentsFiltres = medicamentService.filterBy(filtreNom, filtreCategorie);


        int page = 1;
        int limit = medicamentsFiltres.size();

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }



        // Application du tri
        if (tri != null && !tri.isEmpty()) {
            switch (tri) {
                case "prix-asc":
                    medicamentsFiltres.sort(Comparator.comparing(Medicament::getPrix));
                    break;
                case "prix-desc":
                    medicamentsFiltres.sort(Comparator.comparing(Medicament::getPrix).reversed());
                    break;
                case "nom-asc":
                    medicamentsFiltres.sort(Comparator.comparing(Medicament::getNom));
                    break;
                case "nom-desc":
                    medicamentsFiltres.sort(Comparator.comparing(Medicament::getNom).reversed());
                    break;
            }
        }

        // Pagination
        int total = medicamentsFiltres.size();
        int totalPages = (int) Math.ceil((double) total / limit);
        int fromIndex = (page - 1) * limit;
        int toIndex = Math.min(fromIndex + limit, total);

        List<Medicament> medicaments = medicamentsFiltres.subList(fromIndex, toIndex);

        // Passage des attributs à la JSP
        request.setAttribute("medicaments", medicaments);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("recherche", filtreNom);
        request.setAttribute("categorie", filtreCategorie);
        request.setAttribute("tri", tri);

        request.getRequestDispatcher("catalogue.jsp").forward(request, response);

    }
}