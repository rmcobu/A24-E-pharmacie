package controller;

import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Medicament;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CatalogueServlet", urlPatterns = {"/catalogue"})
public class CatalogueServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("E-pharmacie");
        System.out.println("✅ [INIT] EntityManagerFactory initialisée");
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("🧹 [DESTROY] EntityManagerFactory fermée");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        try {
            String filtreNom = request.getParameter("filtreNom");
            String filtreCategorie = request.getParameter("filtreCategorie");
            String tri = request.getParameter("tri");

            // Requête de base
            String jpql = "SELECT m FROM Medicament m WHERE 1=1";
            if (filtreNom != null && !filtreNom.isEmpty()) {
                jpql += " AND LOWER(m.nom) LIKE :nom";
            }
            if (filtreCategorie != null && !filtreCategorie.isEmpty()) {
                jpql += " AND m.categorie = :categorie";
            }

            TypedQuery<Medicament> query = em.createQuery(jpql, Medicament.class);
            if (filtreNom != null && !filtreNom.isEmpty()) {
                query.setParameter("nom", "%" + filtreNom.toLowerCase() + "%");
            }
            if (filtreCategorie != null && !filtreCategorie.isEmpty()) {
                query.setParameter("categorie", filtreCategorie);
            }

            List<Medicament> medicaments = query.getResultList();
            System.out.println("📦 Médicaments récupérés : " + medicaments.size());

            // Appliquer le tri si demandé
            if (tri != null) {
                switch (tri) {
                    case "prix-asc":
                        medicaments = medicaments.stream()
                                .sorted(Comparator.comparingDouble(Medicament::getPrix))
                                .collect(Collectors.toList());
                        break;
                    case "prix-desc":
                        medicaments = medicaments.stream()
                                .sorted(Comparator.comparingDouble(Medicament::getPrix).reversed())
                                .collect(Collectors.toList());
                        break;
                }
            }

            request.setAttribute("medicaments", medicaments);
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Erreur lors du chargement du catalogue.");
            request.setAttribute("typeMessage", "danger");
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}
