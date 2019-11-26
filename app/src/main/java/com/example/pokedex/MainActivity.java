package com.example.pokedex;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Pokemon> pokemonList;

    public MainActivity(){
        pokemonList = new ArrayList<>();
    }

    private ListView listView;
    private ArrayAdapter<Pokemon> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonList.add(new Pokemon("Pikachu"));
        pokemonList.add(new Pokemon("Squirtle"));
        pokemonList.add(new Pokemon("Meow"));

        adapter = new ArrayAdapter<>(this, R.layout.row, pokemonList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailView.class);
                intent.putExtra("pokemonId", i);
                startActivity(intent);
            }
        });
    }
}