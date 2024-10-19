package org.derum.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Date date;

    private String type;

    protected Transaction(){}


    public static Transaction transactionFactory(){
        return new Transaction();
    }
}
