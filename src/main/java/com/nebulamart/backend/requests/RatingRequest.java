package com.nebulamart.backend.requests;

public class RatingRequest {
    //The product which has to be rated
    private Long productId;

    private double rating;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    

}
