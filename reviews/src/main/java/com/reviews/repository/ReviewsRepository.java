package com.reviews.repository;

import com.reviews.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Ratings, Integer> {
    List<Ratings> getRatingByStarOrderByRatingDesc(String star);
}
