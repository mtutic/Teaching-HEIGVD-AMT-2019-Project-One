package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Movie;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class MoviesDAOTest {
    @EJB
    private IMoviesDAO moviesManager;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAMovie() throws DuplicateKeyException {
        Movie titanic = Movie.builder().title("Titanic").year(1997).build();
        moviesManager.create(titanic);
    }

    @Test
    public void itShouldBePossibleToCreateAndRetrieveAMovieViaTheMoviesDAO() throws DuplicateKeyException,
            KeyNotFoundException {
        Movie movie = Movie.builder().title("Titanic").year(1997).build();
        Movie movieCreated = moviesManager.create(movie);
        movie.setId(movieCreated.getId());
        Movie movieLoaded = moviesManager.findById(String.valueOf(movieCreated.getId()));
        assertEquals(movie, movieCreated);
        assertEquals(movie, movieLoaded);
        assertNotSame(movie, movieLoaded);
    }

    @Test
    public void itShouldBePossibleToDeleteAMovie() throws DuplicateKeyException, KeyNotFoundException {
        Movie movie = Movie.builder().title("Titanic").year(1997).build();
        Movie movieCreated = moviesManager.create(movie);
        movie.setId(movieCreated.getId());
        Movie movieLoaded = moviesManager.findById(String.valueOf(movieCreated.getId()));
        assertEquals(movie, movieCreated);
        moviesManager.deleteById(String.valueOf(movie.getId()));
        boolean hasThrown = false;
        try {
            movieLoaded = moviesManager.findById(String.valueOf(movieCreated.getId()));
        } catch (KeyNotFoundException e) {
            hasThrown = true;
        }
        assertTrue(hasThrown);
    }

    @Test
    public void itShouldBePossibleToUpdateAMovie() throws DuplicateKeyException, KeyNotFoundException {
        Movie movie = Movie.builder().title("Titanic").year(1997).build();
        Movie movieCreated = moviesManager.create(movie);
        movie.setId(movieCreated.getId());
        Movie movieModified = movie.toBuilder().year(2000).build();
        moviesManager.update(movieModified);
        Movie movieModifiedInDB = moviesManager.findById(String.valueOf(movie.getId()));
        assertEquals(movieModified, movieModifiedInDB);
        assertNotEquals(movieCreated, movieModifiedInDB);
    }
}
