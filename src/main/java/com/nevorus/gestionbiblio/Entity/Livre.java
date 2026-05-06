package com.nevorus.gestionbiblio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="livres")
@Getter
@Setter
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String titre;

    @Column(unique = true, nullable = false ,length = 13)
    private String isbn;

    @Column(nullable=false)
    private LocalDateTime date_publication;

    @Column(nullable = false)
    private Integer nb_livres;

    @Column(nullable = false)
    private Boolean disponible = false;
}
