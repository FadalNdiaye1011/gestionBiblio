package com.nevorus.gestionbiblio.DTO.User;

import com.nevorus.gestionbiblio.Enum.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String nom;
    private String email;
    private Role role;
    private Boolean status;
}
