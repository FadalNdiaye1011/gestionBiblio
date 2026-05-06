package com.nevorus.gestionbiblio.repository;

import com.nevorus.gestionbiblio.Entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
