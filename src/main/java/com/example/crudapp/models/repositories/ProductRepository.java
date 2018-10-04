package com.example.crudapp.models.repositories;

import com.example.crudapp.models.entities.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<ProductDTO, Integer> {

}
