package dev.eduardohjsantos.MyRecipeAI.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_item")
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private Integer amount;
    private LocalDateTime expiration;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
