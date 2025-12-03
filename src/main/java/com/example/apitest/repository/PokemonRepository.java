package com.example.apitest.repository;
import com.example.apitest.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
