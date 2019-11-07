package ch.heigvd.amt.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Class used to represent one movie
 * @author : Gilliand Loris, Tutic Mateo
 */
@Builder(toBuilder = true)
@Getter
public class Movie {
    private long id;
    private String title;
    private int year;
}
