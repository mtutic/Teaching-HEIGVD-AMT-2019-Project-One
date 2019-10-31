package ch.heigvd.amt.integration;

public interface IDAO<PK, E> {
    E create(E entity);
    E findById(PK id);
    void update(E entity);
    void deleteById(PK id);
}
