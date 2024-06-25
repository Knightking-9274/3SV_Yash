package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.nebulamart.backend.entities.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    @Query("select r from Review r where r.product.id =:productId")
    public List<Review>getAllProductsReview(@Param("productId")Long productId);
}
