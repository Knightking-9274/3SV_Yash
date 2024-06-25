package com.nebulamart.backend.services;

import org.springframework.data.domain.Page;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.requests.CreateProductRequest;
import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public String deleteProduct(Long productId) throws ProductException;  
    public Product updateProduct(Long productId,Product req ) throws ProductException;
    public Product findProductById(Long id) throws ProductException;
    public List<Product>finProductByCategory(String category);
    public List<Product> findAllProducts();
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice
    ,Integer minDiscount, String sort,String stock,Integer pageNumber, Integer pageSize);
}
