package com.Skill_Master.Skil.controller;

import com.Skill_Master.Skil.entities.Ficha;
import com.Skill_Master.Skil.service.user.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/fichas")
@CrossOrigin("*")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    // Crear nueva ficha
    @PostMapping
    public ResponseEntity<Ficha> createFicha(@RequestBody Ficha ficha) {
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

    // Actualizar ficha
    @PutMapping("/{id}")
    public ResponseEntity<Ficha> updateFicha(@PathVariable Long id, @RequestBody Ficha updatedFicha) {
        Optional<Ficha> ficha = fichaService.updateFicha(id, updatedFicha);
        return ficha.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar ficha
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicha(@PathVariable Long id) {
        fichaService.deleteFicha(id);
        return ResponseEntity.noContent().build();
    }
}
