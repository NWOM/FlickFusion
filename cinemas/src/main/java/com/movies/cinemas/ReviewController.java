package com.movies.cinemas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<Reviews> createReview(@RequestBody Map<String,String> load){
        return new ResponseEntity<Reviews>(reviewService.createReviews(load.get("reviewBody"),load.get("imdbId")), HttpStatus.CREATED);
    }
}
