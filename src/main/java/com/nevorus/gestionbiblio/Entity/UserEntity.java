package com.nevorus.gestionbiblio.Entity;

import com.nevorus.gestionbiblio.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false,unique = true)
    private String email;

    @Column()
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column()
    private Boolean statut;
}
