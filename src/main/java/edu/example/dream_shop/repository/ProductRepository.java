package edu.example.dream_shop.repository;

import edu.example.dream_shop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByBrand(String brand);

    List<Product> findByCategory_NameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByCategory_NameAndName(String categoryName, String name);

    Long countByBrandAndName(String brand, String name);
}
