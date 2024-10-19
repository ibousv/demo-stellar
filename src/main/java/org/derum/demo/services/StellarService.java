package org.derum.demo.services;


import org.derum.demo.entities.Client;
import org.derum.demo.entities.Compte;
import org.derum.demo.repositories.ClientRepository;
import org.derum.demo.repositories.CompteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
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

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;

    private final Server SERVER = new Server("https://horizon-testnet.stellar.org");

    public StellarService(ClientRepository clientRepository, CompteRepository compteRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    /*
    * Gestion des comptes
    */
    public KeyPair generateAccount(){
        return KeyPair.random();
    }

    public void createAccount(Client client){
        KeyPair account = generateAccount();
        String publicKey = account.getAccountId();
        fundAccount(publicKey);

        Compte compte = Compte.builder()
                .id_client(client)
                .cle_public(publicKey)
                .cle_privee(new String(account.getSecretSeed()))
                .build();

        compteRepository.save(compte);
    }

    public void fundAccount(String publicKey){
        String friendbotUrl = String.format(
                "https://friendbot.stellar.org/?addr=%s",
                publicKey);

    }

    public void updateAccount(){

    }

    /*
    * Gestion des transactions
    */
    public void deposit(){

    }

    public void withdraw(){

    }

    public void transfer(){

    }

    public ResponseEntity<?> listTransactions(String publicKey){
        RestClient restClient = RestClient.builder()
                .baseUrl("https://horizon-testnet.stellar.org/accounts")
                .build();

        return restClient.get().uri("/{publicKey}/transactions",publicKey)
                .retrieve()
                .toEntity(Object.class);

    }

    /*
    *  Gestions des utilisateurs
    */
    public void authenticate(){

    }

    public void register(Client client){
        clientRepository.save(client);
        createAccount(client);
    }


    public void  getAccountBalances(String destinataireAccount){
        try {

            AccountResponse account =
                    SERVER
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

    public void transfertMoney(KeyPair source , String to,String amount)  {
        try
        {
        AccountResponse sourceAccount = SERVER.accounts().account(source.getAccountId());
        Transaction tx = new Transaction.Builder(sourceAccount,Network.TESTNET)
                .setBaseFee(Transaction.MIN_BASE_FEE)
                .setTimeout(180)
                .addOperation(new PaymentOperation.Builder(to, new AssetTypeNative(), amount).build())
                .build();

        tx.sign(source);
        SubmitTransactionResponse response = SERVER.submitTransaction(tx);
        System.out.println("La transaction s'est bien passé");
        }
        catch(Exception e){
            System.out.println("La transaction a échoué");
            System.out.println(e.getMessage());
        }
    }


}