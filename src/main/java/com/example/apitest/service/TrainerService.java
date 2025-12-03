package com.example.apitest.service;

import com.example.apitest.Pokemon;
import com.example.apitest.Trainer;
import com.example.apitest.dto.TrainerCreationRequestDTO;
import com.example.apitest.dto.PokeApiResponseDTO;
import com.example.apitest.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final PokeApiService pokeApiService;

    public TrainerService(TrainerRepository trainerRepository, PokeApiService pokeApiService) {
        this.trainerRepository = trainerRepository;
        this.pokeApiService = pokeApiService;
    }

    public Trainer createTrainer(TrainerCreationRequestDTO request) {
        Trainer newTrainer = new Trainer(request.getName());
        return trainerRepository.save(newTrainer);
    }

    public Trainer assignPokemonToTrainer(Long trainerId, String pokemonName) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(trainerId);
        if (optionalTrainer.isEmpty()) {
            throw new RuntimeException("Entrenador no encontrado con ID: " + trainerId);
        }
        PokeApiResponseDTO pokemonDTO = pokeApiService.findPokemon(pokemonName);
        if (pokemonDTO == null) {
            throw new RuntimeException("Pokémon '" + pokemonName + "' no encontrado en la PokeAPI");
        }

        Pokemon newPokemon = new Pokemon();
        newPokemon.setId(pokemonDTO.getId());
        newPokemon.setName(pokemonDTO.getName());

        Trainer trainer = optionalTrainer.get();
        trainer.addPokemonToList(newPokemon);
        return trainerRepository.save(trainer);
    }

    public Trainer getTrainerById(Long trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con ID: " + trainerId));
    }
    
    public Trainer removePokemonFromTrainer(Long trainerId, String pokemonName){
        Trainer trainer = getTrainerById(trainerId);

        Pokemon pokemonToRemove = trainer.getPokemonList().stream()
                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemonName))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("El entrenador no tiene registrado el pokemon con nombre: " + pokemonName));

        trainer.removePokemonFromList(pokemonToRemove);
        return trainerRepository.save(trainer);
    }

    public List<Trainer> findAllTrainers(){
        return trainerRepository.findAll();
    }
}
