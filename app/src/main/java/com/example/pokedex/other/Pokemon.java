package com.example.pokedex.other;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Getter
    private List<String> types;
    @Getter
    private Integer height;
    @Getter
    private Integer weight;
    @Getter
    private String spriteUrl;
    @NonNull
    @Getter
    @Setter
    private Boolean isCaught;
    @Getter
    private List<String> abilities;
    @Getter
    private List<String> moves;
    private List<Map<String, Integer>> stats;

    public List<String> getStatsNames() {
        List<String> statsNames = new ArrayList<>();
        for (int i = 0; i < stats.size(); i++) {
            statsNames.add(stats.get(i).keySet().toArray(new String[0])[0]);
        }
        return statsNames;
    }

    public List<String> getStatsValues() {
        List<String> statsNames = new ArrayList<>();
        for (int i = 0; i < stats.size(); i++) {
            statsNames.add(stats.get(i).values().toArray(new Integer[0])[0].toString());
        }
        return statsNames;
    }

    public Pokemon(String name){
        this.name=name;
    }

    @Override
    public  String toString(){
        return name;
    }
}