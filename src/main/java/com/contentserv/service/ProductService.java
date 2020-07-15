package com.contentserv.service;

import com.contentserv.model.Product;
import com.contentserv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public List<Product> searchProducts(String searchStr) {
        return productRepository.searchProduct(searchStr);
    }

    public Boolean validateProduct(String productName) {
        List<Product> products = productRepository.validateProductName(productName);
        return products.size() > 0 ? true : false;
    }
}
