package dev.eduardohjsantos.MyRecipeAI.service;


import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItem> foodItems){

        String groceries = foodItems.stream()
                .map(item -> String.format("%s (%s) - Amount: %d, Expiration Date: %s",
                        item.getName(), item.getCategory(), item.getAmount(), item.getExpiration()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseando-se em meu banco de dados faça uma receita com os seguintes itens:\n " + groceries;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5.4-mini",
                "reasoning", Map.of("effort", "low"),
                "input", List.of(
                        Map.of("role","system","content","Você é um assitente de receitas"),
                        Map.of("role","user","content",prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var output = (List<Map<String, Object>>) response.get("output");
                    if(output != null && !output.isEmpty()){
                        var message = (Map<String, Object>) output.get(1);
                        var content = (List<Map<String, Object>>) message.get("content");
                        if (content != null && !content.isEmpty()){
                            return content.get(0).get("text").toString();
                        }
                    }
                    return "Nenhuma receita foi gerada";
                });
        // "Now you're a professional chef, suggest a complete recipe using only the ingredients provided. Do not add, assume, or substitute any ingredients that are not explicitly listed. If a full recipe is not possible, explain why."
    }

}
