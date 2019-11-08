package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.integration.IUsersDAO;
import ch.heigvd.amt.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    @EJB
    private IUsersDAO usersManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Check data before creating an user
        User user = User.builder()
                .lastName(req.getParameter("lastname"))
                .firstName(req.getParameter("firstname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
            User storedUser = usersManager.create(user);

            // Bound the user to the session
            req.getSession().setAttribute("user", storedUser);

            resp.sendRedirect(req.getContextPath() + "/");
            return;
        } catch (DuplicateKeyException e) {
            // The user cannot be created in the database
            req.setAttribute("error", "An error has occurred !");
        }

        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
