package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.User;

import javax.ejb.Local;

@Local
public interface IUsersDAO extends IDAO<String, User> {
    User findByEmail(String email) throws KeyNotFoundException;
}
