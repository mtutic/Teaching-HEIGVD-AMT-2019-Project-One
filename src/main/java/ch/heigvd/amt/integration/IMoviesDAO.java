package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Movie;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IMoviesDAO extends IDAO<String, Movie> {

    List<Movie> findAllMovies();
}
