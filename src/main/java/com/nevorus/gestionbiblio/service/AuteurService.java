package com.nevorus.gestionbiblio.service;


import com.nevorus.gestionbiblio.DTO.Auteur.AuteurCreateDto;
import com.nevorus.gestionbiblio.DTO.Auteur.AuteurResponseDto;
import com.nevorus.gestionbiblio.Entity.Auteur;
import com.nevorus.gestionbiblio.Mapper.AuteurMapper;
import com.nevorus.gestionbiblio.repository.AuteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuteurService {
    private final AuteurRepository auteurRepository;
    private final AuteurMapper auteurMapper;

    public List<AuteurResponseDto> findAll() {
        List<Auteur> auteurs = auteurRepository.findAll();
        return auteurMapper.toDto(auteurs);
    }

    public AuteurResponseDto addAuteur(AuteurCreateDto dto) {
        Auteur auteur = new Auteur();
        auteurMapper.applyDtoToEntity(dto, auteur);
        return auteurMapper.toDto(auteurRepository.save(auteur));
    }

    public AuteurResponseDto updateAuteur(AuteurCreateDto dto, Long id) {
        Auteur auteur = auteurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + id));
        auteurMapper.applyDtoToEntity(dto, auteur);
        return auteurMapper.toDto(auteurRepository.save(auteur));
    }

    public AuteurResponseDto deleteAuteur(Long id) {
        Auteur auteur = auteurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + id));
        auteurRepository.delete(auteur);
        return auteurMapper.toDto(auteur);
    }
}
