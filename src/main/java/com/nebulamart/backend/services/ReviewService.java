package com.nebulamart.backend.services;

import com.nebulamart.backend.entities.Review;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.requests.ReviewRequest;
import java.util.List;
public interface ReviewService {
    public Review createReview (ReviewRequest req,User user)throws ProductException; 
    public List<Review> getAllReview(Long productId);
    
}
