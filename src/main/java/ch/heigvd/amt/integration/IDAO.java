package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;

public interface IDAO<PK, E> {
    E create(E entity) throws DuplicateKeyException;
    E findById(PK id) throws KeyNotFoundException;
    void update(E entity) throws KeyNotFoundException;
    void deleteById(PK id) throws KeyNotFoundException;
}
