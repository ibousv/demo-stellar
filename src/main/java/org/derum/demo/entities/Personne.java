package org.derum.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Personne {

    @Id
    private String id;
    private String adresse;
    private String nom;
    private String prenom;
    private String num;
    private String pin;
}
