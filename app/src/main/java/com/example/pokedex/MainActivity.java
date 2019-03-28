package com.example.pokedex;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mPokemonType;
    Button mButtonSearch;
    RecyclerView recycler;
    ArrayList<Pokemon> listaDatos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        //initRecy();

        mButtonSearch.setOnClickListener( view -> {
            String pokemonNumber = mPokemonType.getText().toString().trim();
            if (pokemonNumber.isEmpty()) {
                mPokemonType.setError("Obligatorio");
            } else {

                new FetchPokemonTask().execute(pokemonNumber);
            }
        });

    }

    public void initRecy(){



        for(int i = 0; i <= 50; i++){
            Pokemon pok = new Pokemon("Pokemon: " +i);
            listaDatos.add(pok);
        }

        recycler.setLayoutManager(new LinearLayoutManager(this));

        PokemonAdapter adapter = new PokemonAdapter(listaDatos);
        recycler.setAdapter(adapter);


    }

    private class FetchPokemonTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... pokemonTypes) {
            if(pokemonTypes.length == 0){
                return null;
            }

            String tipo = pokemonTypes[0];

            URL pokeApi = NetworkUtils.buildUrl(tipo);

            try{
                String result = NetworkUtils.getResponseFromHttpUrl(pokeApi);
                return result;
            }catch(IOException e){
                e.printStackTrace();
                return "";
            }

        }

        @Override
        protected void onPostExecute(String pokemonInfo) {
            if (pokemonInfo != null || !pokemonInfo.equals("")) {
                Toast.makeText(MainActivity.this, pokemonInfo,Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(MainActivity.this, "Nothin to show",Toast.LENGTH_LONG).show();
            }
        }
    }

    void bindView(){
        mPokemonType = findViewById(R.id.et_tipo_poke);
        mButtonSearch = findViewById(R.id.bt_search);
        recycler = findViewById(R.id.recycler_id);
    }

}
