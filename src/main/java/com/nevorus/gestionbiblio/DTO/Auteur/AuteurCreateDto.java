package com.nevorus.gestionbiblio.DTO.Auteur;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;


@Data
@AllArgsConstructor

public class AuteurCreateDto {

    @NotBlank(message = "le nom est obligatoire")
    private String nom;
}
