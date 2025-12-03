package com.example.apitest;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pokemon> pokemonList = new ArrayList<>();

    public Trainer(){}

    public Trainer(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPokemonToList(Pokemon newPokemon){
        pokemonList.add(newPokemon);
        newPokemon.setTrainer(this);
    }

    public void removePokemonFromList(Pokemon pokemon) {
        pokemonList.remove(pokemon);
        pokemon.setTrainer(null);
    }

    public List<Pokemon> getPokemonList(){
        return pokemonList;
    }

}
