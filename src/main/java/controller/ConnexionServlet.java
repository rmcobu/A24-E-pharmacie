package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.AuthService;
import dao.ClientDAOImpl;
import model.Client;
import java.io.IOException;

@WebServlet("/login")
public class ConnexionServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService(new ClientDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
       throws ServletException, IOException {
         String email = req.getParameter("email");
         String password = req.getParameter("password");

         authService.authenticate(email, password).ifPresentOrElse(user -> {
           HttpSession session = req.getSession();
           session.setAttribute("user", user);
           try { resp.sendRedirect("accueil.jsp");

           }
           catch (IOException ignored) {}
           }, () -> {
             req.setAttribute("error", "Informations d'identification invalides");
             try { req.getRequestDispatcher("connexion.jsp").forward(req, resp); } catch (Exception ignored) {}
            });
        }
    }





