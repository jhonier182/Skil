package com.Skill_Master.Skil.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long numeroFicha;

    @Column(nullable = false)
    private String nombreFicha;

    @JsonIgnore
    @OneToMany(mappedBy = "ficha")
    private List<User> aprendices;

    // Constructor con parametros
    public Ficha(Long numeroFicha, String nombreFicha) {
        this.numeroFicha = numeroFicha;
        this.nombreFicha = nombreFicha;
    }

    // Constructor vacío
    public Ficha() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(Long numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getNombreFicha() {
        return nombreFicha;
    }

    public void setNombreFicha(String nombreFicha) {
        this.nombreFicha = nombreFicha;
    }

    public List<User> getAprendices() {
        return aprendices;
    }

    public void setAprendices(List<User> aprendices) {
        this.aprendices = aprendices;
    }
}
