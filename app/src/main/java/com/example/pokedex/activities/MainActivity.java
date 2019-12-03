package com.example.pokedex.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;

import com.example.pokedex.database.PokemonDAO;
import com.example.pokedex.other.Pokemon;
import com.example.pokedex.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pokemon> pokemonList;
    private PokemonDAO pokemonDAO;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean resetCaught = sharedPref.getBoolean("reset_caught", false);
        if (resetCaught) {
            pokemonDAO.resetCaught();
        }
    }
}