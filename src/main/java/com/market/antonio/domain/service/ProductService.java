package com.market.antonio.domain.service;

import com.market.antonio.domain.Product;
import com.market.antonio.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//este autoriwer no es la mejor practica
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
//          Una de las formas de hacerlo
//        getProduct(productId).map(product -> {
//            productRepository.delete(productId);
//            return true;
//        }).orElse(false);


        if(getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        }else {
            return false;
        }

    }


    public Product update(Product product){
        return productRepository.update(product);
    }



}
