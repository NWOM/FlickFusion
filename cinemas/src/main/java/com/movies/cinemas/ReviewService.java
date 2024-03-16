package com.movies.cinemas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Reviews createReviews(String reviewBody,String imdbId){
         Reviews reviews=reviewRepository.insert(new Reviews(reviewBody, LocalDateTime.now(),LocalDateTime.now()));

         mongoTemplate.update(Movie.class)
                 .matching(Criteria.where("imdbId").is(imdbId))
                 .apply(new Update().push("reviewIds").value(reviews.getId()))
                 .first();
         return reviews;
    }
}
