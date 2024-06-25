package com.nebulamart.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nebulamart.backend.entities.Review;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.exceptions.UserException;
import com.nebulamart.backend.requests.ReviewRequest;
import com.nebulamart.backend.services.RatingService;
import com.nebulamart.backend.services.ReviewService;
import com.nebulamart.backend.services.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/creates")
    public ResponseEntity<Review>createReview(@RequestBody ReviewRequest req,
                        @RequestHeader("Authorization")String jwt)throws UserException,ProductException{
                        User user = userService.UserProfileByJwt(jwt);
                        Review review = reviewService.createReview(req, user);
                        return new ResponseEntity<Review>(review,HttpStatus.CREATED);
    }
    @GetMapping("product/{productId}")
    public ResponseEntity<List<Review>>getProductsReview(@PathVariable Long productId)throws UserException,ProductException{
        List<Review> review = reviewService.getAllReview(productId);
        return new ResponseEntity<>(review,HttpStatus.ACCEPTED);
    }
}
