package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;

@Stateless
public class UsersDAO implements IUsersDAO {

    @Resource(lookup = "jdbc/movie_history")
    private DataSource dataSource;

    @Override
    public User create(User user) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("INSERT INTO User (Lastname, Firstname, Email, " +
                    "Password) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return user.toBuilder().id(rs.getInt(1)).build();
            }
            return user;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateKeyException("User already exists with email = " + user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public User findById(String idUser) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM User WHERE idUser = ?");
            statement.setString(1, idUser);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new KeyNotFoundException("Could not find user with id = " + idUser);
            }

            return User.builder()
                    .id(rs.getLong(1))
                    .lastName(rs.getString(2))
                    .firstName(rs.getString(3))
                    .email(rs.getString(4))
                    .password(rs.getString(5))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public User findByEmail(String email) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM User WHERE Email = ?");
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new KeyNotFoundException("Could not find user with email = " + email);
            }

            return User.builder()
                    .id(rs.getLong(1))
                    .lastName(rs.getString(2))
                    .firstName(rs.getString(3))
                    .email(rs.getString(4))
                    .password(rs.getString(5))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(User user) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();

            PreparedStatement statement = con.prepareStatement("UPDATE User SET Lastname=?, Firstname=?, Email=?, " +
                    "Password=? WHERE Email = ?");
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());

            int numberOfUpdatedUsers = statement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                throw new KeyNotFoundException("Could not find user with id = " + user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(String email) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM User WHERE Email = ?");
            statement.setString(1, email);

            int numberOfDeletedUsers = statement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
                throw new KeyNotFoundException("Could not find user with email = " + email);
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
