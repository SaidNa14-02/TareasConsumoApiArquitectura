package com.example.apitest.service;

import com.example.apitest.dto.PokeApiResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {
    private final RestTemplate restTemplate;

    public PokeApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokeApiResponseDTO findPokemon(String pokemonName){
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName.toLowerCase();
        try {
            return restTemplate.getForObject(url, PokeApiResponseDTO.class);
        }
        catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}
