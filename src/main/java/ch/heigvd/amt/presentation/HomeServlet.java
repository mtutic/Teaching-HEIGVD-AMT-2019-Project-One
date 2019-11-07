package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IMoviesDAO;
import ch.heigvd.amt.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "")
public class HomeServlet extends HttpServlet {
    @EJB
    private IMoviesDAO moviesManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        User connectedUser = (User) req.getSession().getAttribute("user");

        try {
            req.setAttribute("movies", moviesManager.findSeenMovie(connectedUser));
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("user", connectedUser);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
