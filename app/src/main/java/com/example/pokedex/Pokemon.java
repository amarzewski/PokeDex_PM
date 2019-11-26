package com.example.pokedex;

public class Pokemon {
    private String name;

    public Pokemon(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public  String toString(){
        return name;
    }
}