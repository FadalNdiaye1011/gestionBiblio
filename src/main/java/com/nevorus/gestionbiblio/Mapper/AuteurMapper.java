package com.nevorus.gestionbiblio.Mapper;

import com.nevorus.gestionbiblio.DTO.Auteur.AuteurCreateDto;
import com.nevorus.gestionbiblio.DTO.Auteur.AuteurResponseDto;

import com.nevorus.gestionbiblio.Entity.Auteur;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuteurMapper {

    public AuteurResponseDto toDto(Auteur auteur) {
        return AuteurResponseDto.builder()
                .id(auteur.getId())
                .nom(auteur.getNom())
                .build();
    }

    public List<AuteurResponseDto> toDto(List<Auteur> auteurs) {
        return auteurs.stream().map(this::toDto).toList();
    }


    public void applyDtoToEntity(AuteurCreateDto dto, Auteur auteur) {
        auteur.setNom(dto.getNom());
    }
}
