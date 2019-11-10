package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IMoviesDAO;
import ch.heigvd.amt.integration.IUsersDAO;
import ch.heigvd.amt.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @EJB
    private IUsersDAO usersManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        User connectedUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", connectedUser);
        req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get email and password set by the user
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");

        User connectedUser = (User) req.getSession().getAttribute("user");

        connectedUser.setLastName(lastname);
        connectedUser.setFirstName(firstname);
        connectedUser.setPassword(password);

        try {
            usersManager.update(connectedUser);
            req.getSession().setAttribute("user", connectedUser);
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
    }
}
