package dev.eduardohjsantos.MyRecipeAI.controller;


import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import dev.eduardohjsantos.MyRecipeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }


    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem){
        FoodItem stored = service.save(foodItem);
        return ResponseEntity.ok(stored);
    }


    //GET

    //POST

    //UPDATE

    //DELETE


}
