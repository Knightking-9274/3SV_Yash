package com.nebulamart.backend.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.entities.Review;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.repositories.ProductRepository;
import com.nebulamart.backend.repositories.ReviewRepository;
import com.nebulamart.backend.requests.ReviewRequest;
import com.nebulamart.backend.services.ProductService;
import com.nebulamart.backend.services.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService{

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private ProductService productService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductRepository productRepository, ProductService productService){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
         review.setReview(req.getReview());
         review.setCreatedAt(LocalDateTime.now());
         return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
      return reviewRepository.getAllProductsReview(productId);
    }
    
}
