package org.derum.demo.repositories;

import org.derum.demo.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne,Long> {
}
