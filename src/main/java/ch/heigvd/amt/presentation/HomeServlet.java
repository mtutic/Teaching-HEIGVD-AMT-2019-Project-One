package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
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

@WebServlet(name = "HomeServlet", urlPatterns = {"", "/delete"})
public class HomeServlet extends HttpServlet {
    @EJB
    private IMoviesDAO moviesManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if (action.equals("/delete")) {
            User connectedUser = (User) req.getSession().getAttribute("user");
            long id = Long.parseLong(req.getParameter("id"));

            try {
                moviesManager.deleteSeenMovieById(id, connectedUser.getId());
                req.setAttribute("success", "Update successful !");
            } catch (KeyNotFoundException e) {
                req.setAttribute("error", e.getMessage());
            }
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User connectedUser = (User) req.getSession().getAttribute("user");
        int start = Integer.parseInt(req.getParameter("start"));
        int length = Integer.parseInt(req.getParameter("length"));
        String searchTitle = req.getParameter("search[value]");

        List<Movie> movies = moviesManager.findSeenMovies(connectedUser, start, length, searchTitle);
        int recordsTotal = moviesManager.getNumberOfSeenMovies(connectedUser);

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
