package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IMoviesDAO extends IDAO<String, Movie> {
    List<Movie> findMovies(int start, int length, String searchTitle);
    int getNumberOfMovies();
    List<Movie> findSeenMovies(User user, int start, int lenght, String searchTitle);
    int getNumberOfSeenMovies(User user);
    void deleteSeenMovieById(long movieId, long userId) throws KeyNotFoundException;
    List<Movie> findByTitle(String title) throws KeyNotFoundException;
}
