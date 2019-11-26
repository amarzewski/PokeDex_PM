package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        TextView pokemonName;

        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon("Pikachu"));
        pokemonList.add(new Pokemon("Squirtle"));
        pokemonList.add(new Pokemon("Meow"));

        int pokemonId = getIntent().getIntExtra("pokemonId", -1);

        if (pokemonId < 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Wystąpił błąd", Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        } else {
            pokemonName = findViewById(R.id.pokemonName);
            pokemonName.setText(pokemonList.get(pokemonId).toString());
        }
    }
}
