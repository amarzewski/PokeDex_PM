package com.example.pokedex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.pokedex.other.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PokemonDAO implements DAO<Pokemon> {
    private static OpenHelper dbHelper;

    public PokemonDAO(Context context) {
        dbHelper = new OpenHelper(context);
    }

    public Pokemon getPokemonById(Integer pokemonId) {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from " + PokemonTable.TABLE_NAME + " where " + PokemonTable.Columns.ID + " = " + pokemonId, null);

        Pokemon pokemon;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int idColumnId = cursor.getColumnIndex(PokemonTable.Columns.ID);
            int nameColumnId = cursor.getColumnIndex(PokemonTable.Columns.NAME);
            int typeColumnId = cursor.getColumnIndex(PokemonTable.Columns.TYPES);
            int heightColumnId = cursor.getColumnIndex(PokemonTable.Columns.HEIGHT);
            int weightColumnId = cursor.getColumnIndex(PokemonTable.Columns.WEIGHT);
            int spriteUrlColumnId = cursor.getColumnIndex(PokemonTable.Columns.SPRITE_URL);
            int caughtColumnId = cursor.getColumnIndex(PokemonTable.Columns.CAUGHT);
            int abilitiesColumnId = cursor.getColumnIndex(PokemonTable.Columns.ABILITIES);
            int movesColumnId = cursor.getColumnIndex(PokemonTable.Columns.MOVES);
            int statsColumnId = cursor.getColumnIndex(PokemonTable.Columns.STATS);

            Integer id = cursor.getInt(idColumnId);
            String name = cursor.getString(nameColumnId);
            String typesString = cursor.getString(typeColumnId);
            Integer height = cursor.getInt(heightColumnId);
            Integer weight = cursor.getInt(weightColumnId);
            String spriteUrl = cursor.getString(spriteUrlColumnId);
            Boolean isCaught = Boolean.parseBoolean(cursor.getString(caughtColumnId));
            String abilitiesString = cursor.getString(abilitiesColumnId);
            String movesString = cursor.getString(movesColumnId);
            String statsString = cursor.getString(statsColumnId);

            Gson gson = new Gson();
            Type stringListType = new TypeToken<List<String>>(){}.getType();
            Type mapListType = new TypeToken<List<Map<String, Integer>>>(){}.getType();
            List<String> types = gson.fromJson(typesString, stringListType);
            List<String> abilities = gson.fromJson(abilitiesString, stringListType);
            List<String> moves = gson.fromJson(movesString, stringListType);
            List<Map<String, Integer>> stats = gson.fromJson(statsString, mapListType);

            pokemon = new Pokemon(id, name, types, height, weight, spriteUrl, isCaught, abilities, moves, stats);
            return pokemon;
        }

        return null;
    }

    public List<Pokemon> getAll() {
        String[] columns = new String[] {
            PokemonTable.Columns.ID,
            PokemonTable.Columns.NAME,
            PokemonTable.Columns.CAUGHT,
        };

        Cursor cursor = dbHelper.getReadableDatabase().query(
                PokemonTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        List<Pokemon> results = new ArrayList<>();
        Pokemon pokemon;

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idColumnId = cursor.getColumnIndex(PokemonTable.Columns.ID);
                int nameColumnId = cursor.getColumnIndex(PokemonTable.Columns.NAME);
                int caughtColumnId = cursor.getColumnIndex(PokemonTable.Columns.CAUGHT);

                Integer id = cursor.getInt(idColumnId);
                String name = cursor.getString(nameColumnId);
                Boolean isCaught = Boolean.parseBoolean(cursor.getString(caughtColumnId));

                pokemon = new Pokemon(id, name, isCaught);
                results.add(pokemon);
            }
        }

        cursor.close();

        return results;
    }

    public void setPokemonIsCaughtById(Integer pokemonId, Boolean isCaught) {
        ContentValues cv = new ContentValues();
        cv.put(PokemonTable.Columns.CAUGHT, isCaught.toString());
        dbHelper.getWritableDatabase().update(PokemonTable.TABLE_NAME, cv, " id = ? ", new String[]{pokemonId.toString()});
    }
}
