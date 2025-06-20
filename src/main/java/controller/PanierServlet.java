package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
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

    @PersistenceUnit(unitName = "PharmaciePU")
    private EntityManagerFactory emf;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect("catalogue");
            return;
        }

        int id = Integer.parseInt(idStr);

        EntityManager em = emf.createEntityManager();
        try {
            Medicament med = em.find(Medicament.class, id);
            if (med == null) {
                response.sendRedirect("catalogue");
                return;
            }

            HttpSession session = request.getSession();
            Panier panier = (Panier) session.getAttribute("panier");
            if (panier == null) {
                panier = new Panier();
                session.setAttribute("panier", panier);
            }

            panier.ajouterMedicament(med, 1);
            response.sendRedirect("catalogue");
        } finally {
            em.close();
        }
    }
}
