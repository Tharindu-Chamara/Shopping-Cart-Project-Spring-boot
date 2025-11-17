package edu.example.dream_shop.service;

import edu.example.dream_shop.model.Product;
import edu.example.dream_shop.request.AddProductRequest;

import java.util.List;

public interface ProductService {

    void addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(AddProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryAndBrandName(String category,String name);
    Long countProductsByBrandAndName(String brand,String name);





}
