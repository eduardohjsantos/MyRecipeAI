package dev.eduardohjsantos.MyRecipeAI.controller;


import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import dev.eduardohjsantos.MyRecipeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem){
        FoodItem stored = service.save(foodItem);
        return ResponseEntity.ok(stored);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FoodItem>> list(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FoodItem> listById(@PathVariable Long id){
        return ResponseEntity.ok(service.listById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodItem> update(@PathVariable Long id, @RequestBody FoodItem foodItem){
        return ResponseEntity.ok(service.update(id, foodItem));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(service.listById(id) != null){
            service.delete(id);
            return ResponseEntity.ok("Ingredient with id: " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingredient with id: " + id + " not found");
        }
    }


}
