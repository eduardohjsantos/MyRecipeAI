package dev.eduardohjsantos.MyRecipeAI.controller;


import dev.eduardohjsantos.MyRecipeAI.model.Category;
import dev.eduardohjsantos.MyRecipeAI.model.FoodItem;
import dev.eduardohjsantos.MyRecipeAI.service.FoodItemService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/item/ui")
public class FoodItemUiController {

    private final FoodItemService service;

    public FoodItemUiController(FoodItemService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<FoodItem> items = service.list();
        model.addAttribute("items",items);
        return "listItems";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/item/ui/list";
    }

    @GetMapping("/add")
    public String showFormAddItem(Model model){
        model.addAttribute("item", new FoodItem());
        model.addAttribute("categories", Category.values());
        return "addItem";
    }

    @PostMapping("/save")
    public String addItem(@ModelAttribute FoodItem item, RedirectAttributes redirectAttributes){
        service.save(item);
        redirectAttributes.addFlashAttribute("message", "Item added successfully!");
        return "redirect:/item/ui/list";
    }


}
