package com.example.crudapp.models.services;

import com.example.crudapp.models.entities.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> listAll();
    ProductDTO findProduct(int id);
    void saveProduct(ProductDTO obj);
    void deleteProduct(int id);
}
