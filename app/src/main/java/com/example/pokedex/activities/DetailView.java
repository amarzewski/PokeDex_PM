package com.example.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.database.PokemonDAO;
import com.example.pokedex.other.Pokemon;
import com.example.pokedex.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailView extends AppCompatActivity {
    private PokemonDAO pokemonDAO;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        pokemonDAO = new PokemonDAO(this);

        Switch caughtSwitch = findViewById(R.id.caughtSwitch);

        final int pokemonId = getIntent().getIntExtra("pokemonId", -1);

        caughtSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = isChecked ? "true" : "false";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                pokemon.setIsCaught(isChecked);
                pokemonDAO.setPokemonIsCaughtById(pokemonId, isChecked);
            }
        });


        if (pokemonId < 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Wystąpił błąd", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            ArrayAdapter<String> adapter;
            ListView listView;

            pokemon = pokemonDAO.getPokemonById(pokemonId);
            getSupportActionBar().setTitle(pokemon.getName());

            TextView pokemonName = findViewById(R.id.pokemonName);
            pokemonName.setText(pokemonDAO.getPokemonById(pokemonId).getName());

            caughtSwitch.setChecked(pokemon.getIsCaught());

            adapter = new ArrayAdapter<>(this, R.layout.detail_row, pokemon.getTypes());
            listView = findViewById(R.id.typesListView);
            listView.setAdapter(adapter);

            adapter = new ArrayAdapter<>(this, R.layout.detail_row, pokemon.getAbilities());
            listView = findViewById(R.id.abilitiesListView);
            listView.setAdapter(adapter);

            adapter = new ArrayAdapter<>(this, R.layout.detail_row, pokemon.getMoves());
            listView = findViewById(R.id.movesListView);
            listView.setAdapter(adapter);

            adapter = new ArrayAdapter<>(this, R.layout.detail_row, pokemon.getStatsNames());
            listView = findViewById(R.id.statsNamesListView);
            listView.setAdapter(adapter);

            adapter = new ArrayAdapter<>(this, R.layout.detail_row, pokemon.getStatsValues());
            listView = findViewById(R.id.statsValuesListView);
            listView.setAdapter(adapter);
        }
    }
}
