package com.example.PokeBackend;

public class PokemonData {
    private int id;
    private String name;
    private int baseExperience;
    private int height;
    private boolean isDefault;
    private int order;
    private int weight;
    /*
    private List<PokemonAbility> abilities;
    private List<NamedAPIResource> forms;
    private List<VersionGameIndex> gameIndices;
    private List<PokemonHeldItem> heldItems;
     */
    private String locationAreaEncounters;
    /*
    private List<PokemonMove> moves;
    private List<PokemonTypePast> pastTypes;
    private PokemonSprites sprites;
    private PokemonCries cries;
    private NamedAPIResource species;
    private List<PokemonStat> stats;
    private List<PokemonType> types;
     */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public int getOrder() {
        return order;
    }

    public int getWeight() {
        return weight;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }
}
