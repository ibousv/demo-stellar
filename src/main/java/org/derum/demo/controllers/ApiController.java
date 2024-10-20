package org.derum.demo.controllers;

import org.derum.demo.entities.Client;
import org.derum.demo.services.StellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.stellar.sdk.Transaction;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {

    @Autowired
    StellarService stellarService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Client client){
        stellarService.register(client);
        return ResponseEntity.ok("Vous etes enregistrer avec succes");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Vous etes connecte");
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<String> getAccountBalance(@PathVariable String accountId) {
        return null;
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<?> getTransactionHistory(@PathVariable String accountId) {
        return stellarService.listTransactions(accountId);
    }

}