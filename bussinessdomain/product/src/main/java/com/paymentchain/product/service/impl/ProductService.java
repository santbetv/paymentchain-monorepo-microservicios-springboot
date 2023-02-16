/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.service.impl;


import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paymentchain.product.service.IProductService;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Santiago Betancur
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Override
    @Transactional(readOnly = true) //
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) //
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Override
    @Transactional() //
    public Product save(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    @Transactional() //
    public boolean put(Long id, Product product) {
        try {
            Optional<Product> find = productRepository.findById(id);
            if (!find.isEmpty()) {
                find.get().setId(product.getId());
                find.get().setCode(product.getCode());
                find.get().setName(product.getName());
            } else {
                return false;
            }
            Product save = productRepository.save(find.get());
            productRepository.save(save);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isEmpty()) {
                productRepository.delete(product.get());
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
