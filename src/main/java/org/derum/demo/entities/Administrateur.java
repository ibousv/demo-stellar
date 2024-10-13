package org.derum.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Administrateur extends Client{

    @Id
    private String id;
}
