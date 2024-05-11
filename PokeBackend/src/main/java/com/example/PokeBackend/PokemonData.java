package com.example.PokeBackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonData {
    private int id;
    private String name;
    private int height;
    private int order;
    private int weight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getOrder() {
        return order;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
