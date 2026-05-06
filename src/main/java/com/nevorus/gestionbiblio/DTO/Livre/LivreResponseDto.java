package com.nevorus.gestionbiblio.DTO.Livre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivreResponseDto {
    private Long id;
    private String titre;
    private String isbn;
    private LocalDateTime date_publication;
    private Integer nb_livres;
    private Boolean disponible;
}
