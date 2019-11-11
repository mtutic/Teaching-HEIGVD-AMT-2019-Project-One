package ch.heigvd.amt.presentation;

import ch.heigvd.amt.integration.IMoviesDAO;
import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MoviesServlet", urlPatterns = "/movies")
public class MoviesServlet extends HttpServlet {

    @EJB
    private IMoviesDAO moviesManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        User connectedUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("movies", moviesManager.findAllMovies());
        req.setAttribute("user", connectedUser);
        req.getRequestDispatcher("/WEB-INF/pages/movies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start = Integer.parseInt(req.getParameter("start"));
        int length = Integer.parseInt(req.getParameter("length"));
        String searchTitle = req.getParameter("search[value]");

        List<Movie> movies = moviesManager.findMovies(start, length, searchTitle);
        int recordsTotal = moviesManager.getNumberOfMovies();

        // Create the JSON to send to the datatable
        Gson gson = new Gson();
        HashMap<String, Object> response = new HashMap<>();
        response.put("recordsTotal", recordsTotal);
        response.put("recordsFiltered", recordsTotal);
        response.put("data", movies);

        // Send data as JSON to the datatable
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.write(gson.toJson(response).getBytes());
        out.flush();
    }
}
