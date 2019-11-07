package ch.heigvd.amt.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieTest {

    @Test
    public void shouldCreateAMovie() {
        Movie movie = Movie.builder()
                .id(404)
                .title("Not Found")
                .year(2019)
                .build();

        assertNotNull(movie);

        assertEquals(404, movie.getId());
        assertEquals("Not Found", movie.getTitle());
        assertEquals(2019, movie.getYear());
    }
}
