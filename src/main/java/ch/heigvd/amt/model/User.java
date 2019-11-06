package ch.heigvd.amt.model;

import lombok.Getter;

/**
 * Class used to represent one user
 * @author : Gilliand Loris, Tutic Mateo
 */
@Getter
public class User {

    private long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;


    public User(long id, String lastName, String firstName, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public String name() {
        return firstName + ' ' + lastName;
    }

}
