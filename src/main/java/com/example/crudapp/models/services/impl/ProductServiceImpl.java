package com.example.crudapp.models.services.impl;

import com.example.crudapp.models.entities.ProductDTO;
import com.example.crudapp.models.repositories.ProductRepository;
import com.example.crudapp.models.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("productRepository") ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> listAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO findProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(ProductDTO obj) {
        productRepository.save(obj);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
