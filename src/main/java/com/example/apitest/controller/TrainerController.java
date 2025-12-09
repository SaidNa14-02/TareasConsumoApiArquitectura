package com.example.apitest.controller;

import com.example.apitest.Trainer;
import com.example.apitest.dto.PokemonRequestDTO;
import com.example.apitest.service.TrainerService;
import com.example.apitest.dto.TrainerCreationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;


    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getTrainers(){
        List<Trainer> trainerList = trainerService.findAllTrainers();
        return new ResponseEntity<>(trainerList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody TrainerCreationRequestDTO request) {
        Trainer newTrainer = trainerService.createTrainer(request);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }

    @PostMapping("/{trainerId}/pokemon")
    public ResponseEntity<Trainer> assignPokemonToTrainer(
            @PathVariable Long trainerId,
            @RequestBody PokemonRequestDTO request) {
        try {
            Trainer updatedTrainer = trainerService.assignPokemonToTrainer(trainerId, request.getPokemonName());
            return ResponseEntity.ok(updatedTrainer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{trainerId}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long trainerId) {
        try {
            Trainer trainer = trainerService.getTrainerById(trainerId);
            return ResponseEntity.ok(trainer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{trainerId}/pokemon/{pokemonName}")
    public ResponseEntity<Void> removePokemonFromTrainer (
            @PathVariable Long trainerId,
            @PathVariable String pokemonName
    ){
        try {
            trainerService.removePokemonFromTrainer(trainerId, pokemonName);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{trainerId}")
    public ResponseEntity<Trainer> updateTrainerData (
            @PathVariable Long trainerId,
            @RequestBody TrainerCreationRequestDTO request
    ){
        try {
            Trainer updatedTrainer = trainerService.editTrainerData(trainerId, request.getName());
            return ResponseEntity.ok(updatedTrainer);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
