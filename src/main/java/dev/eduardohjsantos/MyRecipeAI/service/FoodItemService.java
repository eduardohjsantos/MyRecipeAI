package dev.eduardohjsantos.MyRecipeAI.service;


import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import dev.eduardohjsantos.MyRecipeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem save(FoodItem foodItem){
        return repository.save(foodItem);
    }

    public List<FoodItem> list(){
        return repository.findAll();
    }

    public FoodItem listById(Long id){
        Optional<FoodItem> foodItemById =repository.findById(id);
        return foodItemById.orElse(null);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public FoodItem update(Long id, FoodItem foodItem){
        Optional<FoodItem> existingFoodItem = repository.findById(id);
        if(existingFoodItem.isPresent()){
            foodItem.setId(id);
            repository.save(foodItem);
            return foodItem;
        }
        return null;
    }

}
