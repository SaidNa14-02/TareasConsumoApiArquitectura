package com.example.apitest.dto;

// Esta clase es un "molde" para el JSON que envías en la petición POST.
public class TrainerCreationRequestDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}