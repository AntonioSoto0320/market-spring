package com.market.antonio.domain.service;

import com.market.antonio.domain.Purchase;
import com.market.antonio.domain.repository.PurchaseRepository;
import com.market.antonio.persistence.crud.CompraCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByIdCliente(String clientId){
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }





}
