package com.nevorus.gestionbiblio.repository;

import com.nevorus.gestionbiblio.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findByIsbn(String isbn);
}
