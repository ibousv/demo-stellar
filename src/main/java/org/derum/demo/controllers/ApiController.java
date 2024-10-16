package org.derum.demo.controllers;

import org.derum.demo.services.StellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {
    @Autowired
    StellarService stellarService;

    @GetMapping("/balance/{accountId}")
    public String getAccountBalance(@PathVariable String accountId) {
        return null;
    }

    @GetMapping("/transactions/{accountId}")
    public List<String> getTransactionHistory(@PathVariable String accountId) {
        return null;
    }

}
