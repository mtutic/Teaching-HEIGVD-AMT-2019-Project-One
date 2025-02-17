package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IUsersDAO;
import ch.heigvd.amt.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @EJB
    private IUsersDAO usersManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get email and password set by the user
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Check if the user exists in the database
        try {
            User usr = usersManager.findByEmail(email);
            if (usr.getPassword().length() >= 5 && usr.getPassword().equals(password)) {
                // Save the user in the session
                req.getSession().setAttribute("user", usr);
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            } else {
                // The password for the given email is wrong
                req.setAttribute("error", "Wrong password !");
            }
        } catch (KeyNotFoundException e) {
            // The email doesn't exist
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
