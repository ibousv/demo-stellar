package org.derum.demo.controllers;


import org.derum.demo.services.StellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stellar.sdk.KeyPair;

@RestController
@RequestMapping("/api/v1/test")
public class DefaultController {

    @Autowired
    StellarService stellarService;

    @GetMapping("")
    public String index(){
        return "Home API TEST";
   }

  /* @GetMapping("/account")
    public String[] fetchAccount(){
     KeyPair account = stellarService.generateAccount();
       return new String[]{account.getAccountId(),new String(account.getSecretSeed())};
   }*/

   @PostMapping("/transaction/{amount}")
   public void transfert(@PathVariable String amount){
       //KeyPair account = stellarService.generateAccount();
       // Albedo primary account
       KeyPair account = KeyPair.fromSecretSeed("");
       //stellarService.fundAccount(account.getAccountId());
       String DESTINATAIRE = "";
       stellarService.transfertMoney(account, DESTINATAIRE,amount);
       stellarService.getAccountBalance(DESTINATAIRE);
   }

}