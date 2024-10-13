package org.derum.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    private Long id;
    private Double amount;
    private Date date;
    private String type;
}
