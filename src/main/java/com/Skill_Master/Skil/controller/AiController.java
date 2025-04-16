package com.Skill_Master.Skil.controller;

import com.Skill_Master.Skil.service.ai.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin("*")
@Tag(name = "IA", description = "API para servicios de inteligencia artificial")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/ask")
    @Operation(
        summary = "Generar respuesta de IA",
        description = "Genera una respuesta basada en el prompt proporcionado",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<String>> generateResponse(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        if (prompt == null || prompt.trim().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body("El prompt no puede estar vacío"));
        }
        
        return aiService.generateContentResponse(prompt)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/analyze/{userId}")
    @Operation(
        summary = "Analizar rendimiento",
        description = "Analiza el rendimiento del aprendiz basado en su historial",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR') or @userRepository.findById(#userId).get().getEmail() == authentication.name")
    public Mono<ResponseEntity<String>> analyzePerformance(@PathVariable Long userId) {
        return aiService.analyzeUserPerformance(userId)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/plan/{userId}")
    @Operation(
        summary = "Generar plan de formación",
        description = "Genera un plan de formación personalizado para el aprendiz",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR') or @userRepository.findById(#userId).get().getEmail() == authentication.name")
    public Mono<ResponseEntity<String>> generateTrainingPlan(@PathVariable Long userId) {
        return aiService.generateTrainingPlan(userId)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/recommend/{userId}")
    @Operation(
        summary = "Recomendaciones de aprendizaje",
        description = "Proporciona recomendaciones de aprendizaje personalizadas",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR') or @userRepository.findById(#userId).get().getEmail() == authentication.name")
    public Mono<ResponseEntity<String>> getLearningRecommendations(@PathVariable Long userId) {
        return aiService.provideLearningRecommendations(userId)
                .map(ResponseEntity::ok);
    }
} 