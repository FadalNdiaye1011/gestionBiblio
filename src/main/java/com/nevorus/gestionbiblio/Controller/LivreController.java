package com.nevorus.gestionbiblio.Controller;

import com.nevorus.gestionbiblio.DTO.Livre.LivreCreateDTO;
import com.nevorus.gestionbiblio.DTO.Livre.LivreResponseDto;
import com.nevorus.gestionbiblio.Entity.Livre;
import com.nevorus.gestionbiblio.service.LivreService;
import com.nevorus.gestionbiblio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;


    @GetMapping
    public ResponseEntity<List<LivreResponseDto>> getAllLivres() {
        return ResponseEntity.ok(livreService.findAll());
    }

    @PostMapping
    public ResponseEntity<LivreResponseDto> addLivre(@RequestBody LivreCreateDTO livre) {
        return ResponseEntity.ok(livreService.addLivre(livre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreResponseDto> updateLivre(@RequestBody LivreCreateDTO livre, @PathVariable Long id) {
        return ResponseEntity.ok(livreService.updateLivre(livre,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivreResponseDto> deleteLivre(@PathVariable Long id) {
        return ResponseEntity.ok(livreService.deleteLivre(id));
    }
}
