package ch.heigvd.amt.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieTest {

    @Test
    public void shouldCreateAMovie() {
        Movie movie = new Movie(404, "Not Found", 2019);

        assertNotNull(movie);

        assertEquals(404, movie.getId() );
        assertEquals("Not Found", movie.getTitle() );
        assertEquals(2019, movie.getYear() );
    }
}
