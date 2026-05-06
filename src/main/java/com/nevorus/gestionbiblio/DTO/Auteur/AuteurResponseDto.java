package com.nevorus.gestionbiblio.DTO.Auteur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuteurResponseDto {
    private Long id;
    private String nom;
}
