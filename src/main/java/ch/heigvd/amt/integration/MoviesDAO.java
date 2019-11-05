package ch.heigvd.amt.integration;

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
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class MoviesDAO implements IMoviesDAO {

    @Resource(lookup = "jdbc/movie_history")
    private DataSource dataSource;

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie LIMIT 10");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("idMovie");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                movies.add(new Movie(id, title, year));
            }
            connection.close();
            return movies;

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public List<Movie> findSeenMovie(User user) {
        List<Movie> movies = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie INNER JOIN User_has_seen_Movie ON Movie.idMovie = User_has_seen_Movie.Movie_idMovie WHERE User_has_seen_Movie.User_idUser = ?");
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("idMovie");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                movies.add(new Movie(id, title, year));
            }
            connection.close();
            return movies;

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public List<Movie> findByTitle(String titlePattern) {
        List<Movie> movies = new ArrayList<>();

        titlePattern = '%' + titlePattern + '%';

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie WHERE Title LIKE ?");
            preparedStatement.setString(1, titlePattern);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("idMovie");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                movies.add(new Movie(id, title, year));
            }
            connection.close();
            return movies;

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public Movie create(Movie movie) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movie (Title, Year) VALUES (?,?)");

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getYear());

            preparedStatement.execute();
            connection.close();

            return movie;

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public Movie findById(String id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie WHERE idMovie = ?");

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                // TODO faire qqch genre une exception
            }

            connection.close();

            return new Movie(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void update(Movie movie) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Movie " +
                    "SET Title=?, Year=? WHERE idMovie = ?");

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getYear());
            preparedStatement.setLong(3, movie.getId());

            int numberOfUpdatedUsers = preparedStatement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                // TODO faire qqch genre une exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movie WHERE idMovie = ?");

            preparedStatement.setString(1, id);

            int numberOfDeletedUsers = preparedStatement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
                // TODO faire qqch genre une exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }
}
