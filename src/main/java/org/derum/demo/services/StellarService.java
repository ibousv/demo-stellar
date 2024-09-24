package org.derum.demo.services;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;
import org.stellar.sdk.xdr.Preconditions;

import java.io.IOException;

@Service
public class StellarService {

    //private final KeyPair source = KeyPair.random();
    //private final String publicKey = source.getAccountId();
    //private final String  privateKey = new String(source.getSecretSeed());
    private Operation operation;
    Server server = new Server("https://horizon-testnet.stellar.org");

    public KeyPair generateAccount(){
        return KeyPair.random();
    }

    public void  getAccountBalance(String destinataireAccount){
        try {

            AccountResponse account =
                    server
                            .accounts()
                            .account(destinataireAccount);

            System.out.println("Balances for account : " + destinataireAccount);
            for (AccountResponse.Balance balance : account.getBalances()) {
                System.out.printf(
                        "Type: %s, Code: %s, Balance: %s%n",
                        balance.getAssetType(),
                        balance.getAssetCode(),
                        balance.getBalance()
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void fundAccount(String publicKey){
        String friendbotUrl = String.format(
                "https://friendbot.stellar.org/?addr=%s",
                publicKey);
    }

    public void transfertMoney(KeyPair source , String to,String amount)  {
        try
        {
        AccountResponse sourceAccount = server.accounts().account(source.getAccountId());
        Transaction tx = new Transaction.Builder(sourceAccount,Network.TESTNET)
                .setBaseFee(Transaction.MIN_BASE_FEE)
                .setTimeout(180)
                .addOperation(new PaymentOperation.Builder(to, new AssetTypeNative(), amount).build())
                .build();

      /*  TransactionBuilder builder =
                new TransactionBuilder(sourceAccount,Network.TESTNET)
                        .setBaseFee(Transaction.MIN_BASE_FEE)
                        .addPreconditions(TransactionPreconditions.builder()
                                .timeBounds(new TimeBounds(1L,180L))
                                .build())
                        .addOperation(new PaymentOperation.Builder(to, new AssetTypeNative(), amount).build());
        builder.build().sign(source);
        Transaction tx = builder.build();*/
        tx.sign(source);
        SubmitTransactionResponse response = server.submitTransaction(tx);
        System.out.println("La transaction s'est bien passé");
        //System.out.println(response);

        }
        catch(Exception e){
            System.out.println("La transaction a échoué");
            System.out.println(e.getMessage());
        }
    }
}