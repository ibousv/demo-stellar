package org.derum.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Personne {


    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String email;

    private String nom;

    private String prenom;

    private String num;

    @Column(length = 6)
    private String pin;

}
