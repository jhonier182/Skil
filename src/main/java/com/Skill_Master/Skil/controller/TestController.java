package com.Skill_Master.Skil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin("*")
@Tag(name = "Test", description = "API para pruebas de roles y permisos")
public class TestController {

    @GetMapping("/all")
    @Operation(summary = "Contenido público", description = "Esta página es accesible para todos")
    public String allAccess() {
        return "Contenido Público.";
    }

    @GetMapping("/aprendiz")
    @PreAuthorize("hasAuthority('APRENDIZ')")
    @Operation(
        summary = "Contenido para aprendices", 
        description = "Esta página solo es accesible para usuarios con rol de APRENDIZ",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    public String aprendizAccess() {
        return "Contenido de Aprendiz.";
    }

    @GetMapping("/instructor")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @Operation(
        summary = "Contenido para instructores", 
        description = "Esta página solo es accesible para usuarios con rol de INSTRUCTOR",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    public String instructorAccess() {
        return "Contenido de Instructor.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
        summary = "Contenido para administradores", 
        description = "Esta página solo es accesible para usuarios con rol de ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    public String adminAccess() {
        return "Contenido de Administración.";
    }
} 