package org.derum.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Personne {

    @Id
    private String id;
    private String email;
    private String nom;
    private String prenom;
    private String num;

    @Column(length = 6)
    private String pin;
}
