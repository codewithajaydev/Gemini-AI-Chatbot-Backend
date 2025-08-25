package com.ai.gemini_chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        // Construct the request payload
        Map<String, Object> part = Map.of("text", question);
        Map<String, Object> content = Map.of(
                "parts", new Object[]{part},
                "role", "user"
        );
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{content}
        );

        // Build URL with API key as query param
        String urlWithKey = geminiApiUrl + "?key=" + geminiApiKey;

        try {
            // Make API Call
            String response = webClient.post()
                    .uri(urlWithKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response;
        } catch (Exception e) {
            // Log the error and return it in the response
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
}
