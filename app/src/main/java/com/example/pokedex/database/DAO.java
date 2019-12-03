package com.example.pokedex.database;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T getPokemonById(Integer pokemonId);
    void setPokemonIsCaughtById(Integer pokemonId, Boolean isCaught);
    void resetCaught();
}
