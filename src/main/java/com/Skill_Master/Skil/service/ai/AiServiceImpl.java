package com.Skill_Master.Skil.service.ai;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AiServiceImpl implements AiService {
    
    private final WebClient webClient;
    
    @Autowired
    private UserRepository userRepository;
    
    @Value("${ai.api.url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;
    
    @Value("${ai.api.key:demo-api-key}")
    private String apiKey;
    
    @Value("${ai.api.model:gpt-3.5-turbo}")
    private String model;
    
    public AiServiceImpl() {
        this.webClient = WebClient.builder().build();
    }
    
    @Override
    public Mono<String> generateContentResponse(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        
        JSONArray messagesArray = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messagesArray.put(message);
        
        requestBody.put("messages", messagesArray);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);
        
        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractContentFromResponse)
                .onErrorResume(e -> {
                    return Mono.just("Lo siento, no he podido procesar tu solicitud en este momento.");
                });
    }
    
    @Override
    public Mono<String> analyzeUserPerformance(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return Mono.just("Usuario no encontrado.");
        }
        
        User user = userOptional.get();
        String prompt = String.format(
                "Analiza el rendimiento del aprendiz %s basado en su historial. Proporciona una evaluación detallada y recomendaciones para mejorar.",
                user.getNombre());
        
        return generateContentResponse(prompt);
    }
    
    @Override
    public Mono<String> generateTrainingPlan(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return Mono.just("Usuario no encontrado.");
        }
        
        User user = userOptional.get();
        String prompt = String.format(
                "Crea un plan de formación personalizado para el aprendiz %s. El plan debe incluir objetivos, recursos y cronograma recomendado.",
                user.getNombre());
        
        return generateContentResponse(prompt);
    }
    
    @Override
    public Mono<String> provideLearningRecommendations(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return Mono.just("Usuario no encontrado.");
        }
        
        User user = userOptional.get();
        String prompt = String.format(
                "Recomienda recursos de aprendizaje adicionales para el aprendiz %s basado en su perfil y habilidades actuales.",
                user.getNombre());
        
        return generateContentResponse(prompt);
    }
    
    private String extractContentFromResponse(String responseJson) {
        try {
            JSONObject responseObj = new JSONObject(responseJson);
            JSONArray choices = responseObj.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject firstChoice = choices.getJSONObject(0);
                JSONObject message = firstChoice.getJSONObject("message");
                return message.getString("content").trim();
            }
            return "No se pudo obtener una respuesta clara.";
        } catch (Exception e) {
            return "Error al procesar la respuesta del modelo.";
        }
    }
} 