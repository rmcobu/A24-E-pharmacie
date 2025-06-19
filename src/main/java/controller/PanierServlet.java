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
    public void init() {
        service = new MedicamentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action       = req.getParameter("action");
        HttpSession session = req.getSession();

        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        if("vider".equals(action)) {
            panier.clear();
        } else {

            int idMedicament = Integer.parseInt(req.getParameter("id"));

            Medicament med = service.findById(idMedicament);
            if (med == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
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
            }
        }
            boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
            if (isAjax) {
                resp.setContentType("application/json;charset=UTF-8");

                StringBuilder json = new StringBuilder();
                json.append("{");
                json.append("\"totalItemsCount\":").append(panier.getTotalItemsCount()).append(",");
                json.append("\"total\":").append(panier.getTotal()).append(",");
                json.append("\"items\":[");
                boolean first = true;
                for (var entry : panier.getItems().entrySet()) {
                    if (!first) json.append(",");
                    first = false;
                    var m = entry.getKey();
                    int q = entry.getValue();
                    json.append("{")
                            .append("\"id\":").append(m.getId()).append(",")
                            .append("\"nom\":\"").append(m.getNom()).append("\",")
                            .append("\"prix\":").append(m.getPrix()).append(",")
                            .append("\"image\":\"").append(m.getImage()).append("\",")
                            .append("\"categorie\":\"").append(m.getCategorie()).append("\",")
                            .append("\"quantite\":").append(q)
                            .append("}");
                }
                json.append("]}");
                resp.getWriter().write(json.toString());
            } else {

                resp.sendRedirect(req.getContextPath() + "/catalogue");
            }
        }

}
