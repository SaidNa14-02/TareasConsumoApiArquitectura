package com.example.apitest;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Pokemon {
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    private Trainer trainer;

    public Pokemon(){
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Trainer getTrainer(){
        return trainer;
    }
    
    public void setTrainer(Trainer trainer){
        this.trainer = trainer;
    }
}
