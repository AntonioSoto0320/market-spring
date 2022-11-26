package com.market.antonio.web.controller;

import com.market.antonio.domain.Purchase;
import com.market.antonio.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(),HttpStatus.OK) ;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Purchase>> getByIdCliente(@PathVariable("id") String clientId){
        return purchaseService.getByIdCliente(clientId).filter(purchases-> !purchases.isEmpty())
                .map(purchases-> new ResponseEntity<>(purchases,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }



}
