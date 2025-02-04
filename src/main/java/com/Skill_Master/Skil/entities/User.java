package com.Skill_Master.Skil.entities;

import com.Skill_Master.Skil.Enums.UserRole;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo")
    private String email;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "Doc_identidad")
    private Long doc_identidad;

    @Column(name = "rol")
    private UserRole role;

    // Relación con la ficha
    @ManyToOne
    @JoinColumn(name = "ficha", referencedColumnName = "id")
    private Ficha ficha;


    @Column(name = "profile_picture")
    private String profilePicture;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private Estado estado;



    public User() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDoc_identidad() {
        return doc_identidad;
    }

    public void setDoc_identidad(Long doc_identidad) {
        this.doc_identidad = doc_identidad;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
