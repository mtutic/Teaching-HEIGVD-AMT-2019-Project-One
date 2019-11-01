package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO implements IUsersDAO {

    @Resource(lookup = "jdbc/movie_history")
    private DataSource dataSource;

    @Override
    public User create(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (lastname, firstname, email, password ) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.execute();
            connection.close();

            return user;

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public User findById(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                // TODO faire qqch genre une exception
            }

            connection.close();

            return new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

        } catch (SQLException ex) {
            Logger.getLogger(MoviesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET lastname=?, firstname=?, email=?, password=? WHERE email = ?");

            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());

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
    public void deleteById(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE email = ?");

            preparedStatement.setString(1, email);

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
