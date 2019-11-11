package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IMoviesDAO extends IDAO<String, Movie> {
    // TODO Remove this method at the end
    List<Movie> findAllMovies();
    List<Movie> findMovies(int start, int length, String searchTitle);
    int getNumberOfMovies();
    List<Movie> findSeenMovie(User user) throws KeyNotFoundException;
    List<Movie> findByTitle(String title) throws KeyNotFoundException;
}
