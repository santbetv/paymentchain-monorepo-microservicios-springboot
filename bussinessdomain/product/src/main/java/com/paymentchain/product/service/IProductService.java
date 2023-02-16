/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.service;

import com.paymentchain.product.entities.Product;
import java.util.List;

/**
 *
 * @author Santiago Betancur
 */
public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
    
    public Product save(Product customer);
    public boolean put(Long id,Product product);
    public boolean delete(Long id);

}
