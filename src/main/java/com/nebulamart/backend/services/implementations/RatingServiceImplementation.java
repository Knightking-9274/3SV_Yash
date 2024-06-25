package com.nebulamart.backend.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.entities.Rating;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.repositories.RatingRepository;
import com.nebulamart.backend.requests.RatingRequest;
import com.nebulamart.backend.services.ProductService;
import com.nebulamart.backend.services.RatingService;

@Service
public class RatingServiceImplementation implements RatingService{
    private RatingRepository ratingRepository;

    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService){
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
       Product product = productService.findProductById(req.getProductId());
       Rating rating = new Rating();
       rating.setProduct(product);
       rating.setUser(user);
       rating.setRating(req.getRating());
       rating.setCreatedAt(LocalDateTime.now());
       return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
    
}
