package com.reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Ratings {
    @Id
    private int id;

    private String flightName;
    private String content;
    private String star;
    private double rating;

}
