package ch.heigvd.amt.datastore.exceptions;

public class DuplicateKeyException extends Exception {
    public DuplicateKeyException(String message) {
        super(message);
    }
}
