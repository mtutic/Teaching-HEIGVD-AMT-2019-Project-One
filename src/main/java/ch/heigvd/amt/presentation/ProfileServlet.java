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

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile", "/profile/delete"})
public class ProfileServlet extends HttpServlet {

    @EJB
    private IUsersDAO usersManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User connectedUser = (User) req.getSession().getAttribute("user");

        if (req.getServletPath().equals("/profile")) {
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("user", connectedUser);
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/profile/delete")) {
            try {
                usersManager.deleteById(connectedUser.getEmail());
                resp.sendRedirect(req.getContextPath() + "/logout");
            } catch (KeyNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (validateForm(req)) {
            User connectedUser = (User) req.getSession().getAttribute("user");

            User user = User.builder()
                    .lastName(req.getParameter("lastname"))
                    .firstName(req.getParameter("firstname"))
                    .email(connectedUser.getEmail())
                    .password(req.getParameter("password"))
                    .build();

            try {
                usersManager.update(user);
                req.getSession().setAttribute("user", user);
                req.setAttribute("success", "Update successful !");
            } catch (KeyNotFoundException e) {
                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    private boolean validateForm(HttpServletRequest req) {
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String password = req.getParameter("password");

        if (lastname.isEmpty()) {
            req.setAttribute("error", "Lastname cannot be empty");
        } else if (firstname.isEmpty()) {
            req.setAttribute("error", "Firstname cannot be empty");
        } else if (password.isEmpty() || password.length() < 5) {
            req.setAttribute("error", "Wrong password");
        } else {
            return true;
        }

        return false;
    }
}
