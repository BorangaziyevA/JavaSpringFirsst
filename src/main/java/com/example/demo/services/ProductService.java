package com.example.demo.services;

import java.util.Date;

import com.example.demo.entity.Product;
import com.example.demo.requests.ProductRequest;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product create(ProductRequest request) {
        request.setDate(new Date());
        return productRepository.save(new Product(request));
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id.longValue()).orElse(null);
    }
    public boolean update(Integer id, String newName, String newPrice) {
        Product update = getProduct(id);
        update.setName(newName);
        update.setPrice(newPrice);
        productRepository.save(update);
        return true;
    }


        public Product delete(Integer id) {
        return productRepository.findById(id.longValue()).orElse(null);
    }


}




