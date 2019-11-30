package com.example.pokedex.other;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
public class Pokemon {
    enum Type {
        NORMAL,
        FIGHTING,
        FLYING,
        POISON,
        GROUND,
        ROCK,
        BUG,
        GHOST,
        STEEL,
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        PSYCHIC,
        ICE,
        DRAGON,
        DARK,
        FAIRY,
        UNKNOWN,
        SHADOW
    }

    @NonNull
    @Getter
    private Integer id;
    @NonNull
    @Getter
    private String name;
    private List<String> types;
    private Integer height;
    private Integer weight;
    private String spriteUrl;
    @NonNull
    @Getter
    @Setter
    private Boolean isCaught;
    private List<String> abilities;
    private List<String> moves;
    private List<Map<String, Integer>> stats;

    public Pokemon(String name){
        this.name=name;
    }

    @Override
    public  String toString(){
        return name;
    }
}