package com.Skill_Master.Skil.service.ai;

import reactor.core.publisher.Mono;

public interface AiService {
    Mono<String> generateContentResponse(String prompt);
    Mono<String> analyzeUserPerformance(Long userId);
    Mono<String> generateTrainingPlan(Long userId);
    Mono<String> provideLearningRecommendations(Long userId);
} 