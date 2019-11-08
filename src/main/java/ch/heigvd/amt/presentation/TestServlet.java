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

// TODO Remove this webservlet at the end
@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @EJB
    private IMoviesDAO moviesManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try {
            req.setAttribute("movies", moviesManager.findByTitle("pocalypse"));
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/pages/movies.jsp").forward(req, resp);
    }
}
