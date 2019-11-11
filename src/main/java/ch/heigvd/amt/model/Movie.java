package ch.heigvd.amt.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to represent one movie
 * @author : Gilliand Loris, Tutic Mateo
 */
@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@Setter
public class Movie {
    private long id;
    private String title;
    private int year;
}
