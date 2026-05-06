package com.nevorus.gestionbiblio.DTO.User;

import com.nevorus.gestionbiblio.Enum.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    private String nom;

    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Email(message = "L'email n'est pas valide")
    private String email;

    @NotBlank(message = "le role est obligatoire")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "le statut est obligatoire")
    private Boolean status;


}
