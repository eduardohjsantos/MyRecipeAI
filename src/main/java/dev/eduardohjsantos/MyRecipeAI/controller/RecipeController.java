package dev.eduardohjsantos.MyRecipeAI.controller;

import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import dev.eduardohjsantos.MyRecipeAI.service.ChatGptService;
import dev.eduardohjsantos.MyRecipeAI.service.FoodItemService;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private FoodItemService service;
    private ChatGptService chatGptService;

    public RecipeController(FoodItemService service, ChatGptService chatGptService) {
        this.service = service;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<FoodItem> foodItems = service.list();
        return chatGptService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
