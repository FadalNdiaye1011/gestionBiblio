package com.nevorus.gestionbiblio.Controller;

import com.nevorus.gestionbiblio.DTO.Auteur.AuteurCreateDto;
import com.nevorus.gestionbiblio.DTO.Auteur.AuteurResponseDto;
import com.nevorus.gestionbiblio.service.AuteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auteurs")
@RequiredArgsConstructor
public class AuteurController {
    private final AuteurService auteurService;

    @GetMapping
    public ResponseEntity<List<AuteurResponseDto>> findAll() {
        return ResponseEntity.ok(auteurService.findAll());
    }

    @PostMapping
    public ResponseEntity<AuteurResponseDto> save(@RequestBody AuteurCreateDto dto) {
        return ResponseEntity.ok(auteurService.addAuteur(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuteurResponseDto> update(@PathVariable Long id, @RequestBody AuteurCreateDto dto) {
        return ResponseEntity.ok(auteurService.updateAuteur(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuteurResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(auteurService.deleteAuteur(id));
    }
}
