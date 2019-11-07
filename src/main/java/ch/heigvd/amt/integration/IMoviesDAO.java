package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IMoviesDAO extends IDAO<String, Movie> {
    List<Movie> findAllMovies();
    List<Movie> findSeenMovie(User user);
    List<Movie> findByTitle(String title);
}
