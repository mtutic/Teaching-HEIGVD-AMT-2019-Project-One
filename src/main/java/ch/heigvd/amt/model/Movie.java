package ch.heigvd.amt.model;

import lombok.Getter;

/**
 * Class used to represent one movie
 * @author : Gilliand Loris, Tutic Mateo
 */
@Getter
public class Movie {

    private long id;
    private String title;
    private int year;

    public Movie(long id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }
}
