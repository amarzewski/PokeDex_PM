package com.example.pokedex.activities;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pokedex.database.PokemonDAO;
import com.example.pokedex.other.Pokemon;
import com.example.pokedex.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pokemon> pokemonList;
    protected static PokemonDAO pokemonDAO;
    private ListView listView;
    private ArrayAdapter<Pokemon> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonDAO = new PokemonDAO(this);
        pokemonList = pokemonDAO.getAll();

        adapter = new ArrayAdapter<>(this, R.layout.main_row, pokemonList);
        listView = findViewById(R.id.pokemonListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailView.class);
                intent.putExtra("pokemonId", pokemonList.get(i).getId());
                startActivity(intent);
            }
        });
    }
}