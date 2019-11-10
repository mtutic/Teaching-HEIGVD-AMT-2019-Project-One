package ch.heigvd.amt.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to represent one user
 * @author : Gilliand Loris, Tutic Mateo
 */
@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@Setter
public class User {
    private long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;

    public String name() {
        return firstName + ' ' + lastName;
    }
}
