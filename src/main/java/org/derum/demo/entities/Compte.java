package org.derum.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private double solde;

    private String cle_privee;

    private String cle_public;

}
