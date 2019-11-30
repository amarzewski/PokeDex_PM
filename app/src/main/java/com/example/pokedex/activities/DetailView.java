package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.database.PokemonDAO;
import com.example.pokedex.other.Pokemon;
import com.example.pokedex.R;

import java.util.ArrayList;
import java.util.List;

public class DetailView extends AppCompatActivity {
    private PokemonDAO pokemonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        getSupportActionBar().setTitle("pokemon detail");

        pokemonDAO = new PokemonDAO(this);
        TextView pokemonName;


        int pokemonId = getIntent().getIntExtra("pokemonId", -1);

        if (pokemonId < 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Wystąpił błąd", Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        } else {
            pokemonName = findViewById(R.id.pokemonName);
            pokemonName.setText(pokemonDAO.getPokemonById(pokemonId).getName());
        }
    }


}
