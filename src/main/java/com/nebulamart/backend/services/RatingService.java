package com.nebulamart.backend.services;

import com.nebulamart.backend.entities.Rating;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.requests.RatingRequest;
import java.util.List;

public interface RatingService {
    public Rating createRating (RatingRequest req,User user)throws ProductException;
    public List<Rating> getProductRating(Long productId);
}
