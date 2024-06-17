package com.example.PokeBackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.util.LinkedHashMap;
import java.time.Instant;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int height;

    @Column(name = "\"order\"")
    private int order;
    private int weight;
    private LinkedHashMap sprites;

    private String frontDefault;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSprites(LinkedHashMap sprites) {
        this.sprites = sprites;
    }

    public String getFrontDefault(){
        LinkedHashMap LM = this.sprites;
        this.frontDefault = LM.get("front_default").toString();
        return frontDefault;
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}

