package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MoviesDAO implements IMoviesDAO {

    @Resource(lookup = "jdbc/movie_history")
    private DataSource dataSource;

    @Override
    public List<Movie> findAllMovies() {
        Connection con = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = dataSource.getConnection();

            // TODO Remove limit and work with pagination... ahaha
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Movie LIMIT 10");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("idMovie");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                movies.add(Movie.builder().id(id).title(title).year(year).build());
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Movie> findMovies(int start, int length) {
        Connection con = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Movie LIMIT ?, ?");
            statement.setInt(1, start);
            statement.setInt(2, length);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("idMovie");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                movies.add(Movie.builder().id(id).title(title).year(year).build());
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public int getNumberOfMovies() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM Movie");
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Movie> findSeenMovie(User user) {
        Connection con = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Movie INNER JOIN User_has_seen_Movie ON" +
                    " Movie.idMovie = User_has_seen_Movie.Movie_idMovie WHERE User_has_seen_Movie.User_idUser = ?");
            statement.setLong(1, user.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("idMovie");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                movies.add(Movie.builder().id(id).title(title).year(year).build());
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Movie> findByTitle(String titlePattern) {
        Connection con = null;
        List<Movie> movies = new ArrayList<>();
        titlePattern = '%' + titlePattern + '%';

        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Movie WHERE Title LIKE ?");
            statement.setString(1, titlePattern);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("idMovie");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                movies.add(Movie.builder().id(id).title(title).year(year).build());
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Movie create(Movie movie) {
        Connection con = null;
        try {
            con = dataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO Movie (Title, Year) VALUES (?,?)");
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getYear());
            statement.execute();

            return movie;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Movie findById(String id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Movie WHERE idMovie = ?");
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new KeyNotFoundException("Could not find movie with id = " + id);
            }

            return Movie.builder()
                    .id(rs.getLong(1))
                    .title(rs.getString(2))
                    .year(rs.getInt(3))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Movie movie) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE Movie SET Title=?, Year=? WHERE idMovie = ?");
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getYear());
            statement.setLong(3, movie.getId());

            int numberOfUpdatedMovies = statement.executeUpdate();
            if (numberOfUpdatedMovies != 1) {
                throw new KeyNotFoundException("Could not find movie with id = " + movie.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(String id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM Movie WHERE idMovie = ?");
            statement.setString(1, id);

            int numberOfDeletedMovies = statement.executeUpdate();
            if (numberOfDeletedMovies != 1) {
                throw new KeyNotFoundException("Could not find movie with id = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
