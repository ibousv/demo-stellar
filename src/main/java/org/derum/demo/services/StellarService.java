package org.derum.demo.services;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

@Service
public class StellarService {
    /*
    @TODO
    Refactoring du Service
    -> deposit
    -> withdraw
    -> transfer
    -> fetchPayments
   */


    private final Server server = new Server("https://horizon-testnet.stellar.org");

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

        tx.sign(source);
        SubmitTransactionResponse response = server.submitTransaction(tx);
        System.out.println("La transaction s'est bien passé");
        }
        catch(Exception e){
            System.out.println("La transaction a échoué");
            System.out.println(e.getMessage());
        }
    }
}