package com.Skill_Master.Skil.controller;

import com.Skill_Master.Skil.entities.Ficha;
import com.Skill_Master.Skil.service.user.FichaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/fichas")
@CrossOrigin("*")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    // Crear nueva ficha - Solo administradores o instructores
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Ficha> createFicha(@Valid @RequestBody Ficha ficha) {
        Ficha createdFicha = fichaService.createFicha(ficha);
        return new ResponseEntity<>(createdFicha, HttpStatus.CREATED);
    }

    // Obtener todas las fichas
    @GetMapping
    public ResponseEntity<List<Ficha>> getAllFichas() {
        List<Ficha> fichas = fichaService.getAllFichas();
        return new ResponseEntity<>(fichas, HttpStatus.OK);
    }

    // Obtener ficha por número de ficha
    @GetMapping("/{numeroFicha}")
    public ResponseEntity<Ficha> getFichaByNumero(@PathVariable Long numeroFicha) {
        Optional<Ficha> ficha = fichaService.findByNumeroFicha(numeroFicha);
        return ficha.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar ficha - Solo administradores o instructores
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Ficha> updateFicha(@PathVariable Long id, @Valid @RequestBody Ficha updatedFicha) {
        Optional<Ficha> ficha = fichaService.updateFicha(id, updatedFicha);
        return ficha.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar ficha - Solo administradores
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteFicha(@PathVariable Long id) {
        fichaService.deleteFicha(id);
        return ResponseEntity.noContent().build();
    }
}
