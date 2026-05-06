package com.nevorus.gestionbiblio.service;

import com.nevorus.gestionbiblio.DTO.Livre.LivreCreateDTO;
import com.nevorus.gestionbiblio.DTO.Livre.LivreResponseDto;
import com.nevorus.gestionbiblio.Entity.Livre;
import com.nevorus.gestionbiblio.Mapper.LivreMapper;
import com.nevorus.gestionbiblio.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivreService {
    private final LivreRepository livreRepository;
    private final LivreMapper livreMapper;

    public List<LivreResponseDto> findAll() {
        List<Livre> livres = livreRepository.findAll();
        return livreMapper.toDto(livres);
    }

    public LivreResponseDto addLivre(LivreCreateDTO livreCreateDTO) {
        if(livreRepository.findByIsbn(livreCreateDTO.getIsbn()).isPresent()) {
            throw new RuntimeException("Isbn déjà utilisé : " + livreCreateDTO.getIsbn());
        }
        Livre livre = new Livre();
        livreMapper.applyDtoToEntity(livreCreateDTO, livre);
        return livreMapper.toDto(livreRepository.save(livre));
    }

    public LivreResponseDto updateLivre(LivreCreateDTO livreCreateDTO, Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce livre n'existe pas dans cette bibliothèque"));


        livreRepository.findByIsbn(livreCreateDTO.getIsbn())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new RuntimeException("Isbn déjà utilisé : " + livreCreateDTO.getIsbn());
                    }
                });

        livreMapper.applyDtoToEntity(livreCreateDTO, livre);
        return livreMapper.toDto(livreRepository.save(livre));
    }



    public LivreResponseDto deleteLivre(Long id) {
        Livre livre = livreRepository.findById(id).orElseThrow(() -> new RuntimeException("Livre not found"));
        livreRepository.delete(livre);
        return livreMapper.toDto(livre);
    }
}
