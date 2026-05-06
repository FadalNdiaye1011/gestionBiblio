package com.nevorus.gestionbiblio.DTO.Livre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LivreCreateDTO {

    @NotBlank(message = "le titre est obligatoire")
    private String titre;

    @NotBlank(message = "le isbn est obligatoire")
    @Size(min = 10, max = 13)
    private String isbn;

    @NotNull(message = "la date de publication est obligatoire")
    private LocalDateTime date_publication;

    @NotNull(message = "le nombre de livres est obligatoire")
    private Integer nb_livres;

    private boolean disponible;


}
