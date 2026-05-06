package com.nevorus.gestionbiblio.Mapper;

import com.nevorus.gestionbiblio.DTO.Livre.LivreCreateDTO;
import com.nevorus.gestionbiblio.DTO.Livre.LivreResponseDto;

import com.nevorus.gestionbiblio.Entity.Livre;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivreMapper {

    public LivreResponseDto toDto(Livre livre) {
        return LivreResponseDto.builder()
                .id(livre.getId())
                .titre(livre.getTitre())
                .isbn(livre.getIsbn())
                .nb_livres(livre.getNb_livres())
                .date_publication(livre.getDate_publication())
                .disponible(livre.getDisponible())
                .build();
    }

    public List<LivreResponseDto> toDto(List<Livre> livres) {
        return livres.stream()
                .map(this::toDto)
                .toList();
    }

    public void applyDtoToEntity(LivreCreateDTO dto, Livre livre) {
        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        livre.setNb_livres(dto.getNb_livres());
        livre.setDate_publication(dto.getDate_publication());
        livre.setDisponible(dto.isDisponible());
    }

}
