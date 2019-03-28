package com.example.pokedex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pokedex.models.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolderObj>{

    private ArrayList<Pokemon> listaDato;

    public PokemonAdapter(ArrayList<Pokemon> listaDatos) {
        this.listaDato = listaDatos;
    }

    @Override
    public ViewHolderObj onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items,parent,false);
        return new ViewHolderObj(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderObj viewHolderObj, int i) {
        viewHolderObj.asign(listaDato.get(i));
    }


    @Override
    public int getItemCount() {
        return listaDato.size();
    }

    class ViewHolderObj extends RecyclerView.ViewHolder {

        public TextView nameText;

        public ViewHolderObj(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.id_name);
        }

        public void asign(final Pokemon s) {
            nameText.setText(s.getName());
        }
    }
}
