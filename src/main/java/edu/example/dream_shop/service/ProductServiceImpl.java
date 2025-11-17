package edu.example.dream_shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.example.dream_shop.exception.CategoryNotFoundException;
import edu.example.dream_shop.exception.ProductNotFoundException;
import edu.example.dream_shop.model.Product;
import edu.example.dream_shop.repository.CategoryRepository;
import edu.example.dream_shop.repository.ProductRepository;
import edu.example.dream_shop.request.AddProductRequest;
import edu.example.dream_shop.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ObjectMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void addProduct(AddProductRequest product) {
        Product product1 = mapper.convertValue(product, Product.class);
        Category category = (edu.example.dream_shop.model.Category)categoryRepository.findByName(
                 product1.getCategory().getName()).orElseThrow(
                () -> new CategoryNotFoundException("Not found this category plz try again")
        );
        product1.setCategory(category);
        repository.save(product1);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found in this Id "+id)
        );
    }

    @Override
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateProduct(AddProductRequest product, Long productId) {
        Product product2 = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with this id"));

       Category  category =(edu.example.dream_shop.model.Category) categoryRepository
                .findByName(product.getCategory().getName())
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found Please Try again"));
        product2.setName(product.getName());
        product2.setBrand(product.getBrand());
        product2.setPrice(product.getPrice());
        product2.setInventory(product.getInventory());
        product2.setDescription(product.getDescription());
        product2.setCategory(category);

        // Update images
        product2.getImages().clear();
        if (product.getImages() != null) {
            product.getImages().forEach(img -> {
                img.setProduct(product2);
                product2.getImages().add(img);
            });
        }

        repository.save(product2);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return  repository.findByCategory_Name(category);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return  repository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return repository.findByCategory_NameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return  repository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrandName(String category, String name) {
        return  repository.findByCategory_NameAndName(category,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return repository.countByBrandAndName(brand,name);
    }
}
