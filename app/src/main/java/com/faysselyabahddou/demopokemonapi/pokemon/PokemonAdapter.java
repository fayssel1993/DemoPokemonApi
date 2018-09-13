package com.faysselyabahddou.demopokemonapi.pokemon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.faysselyabahddou.demopokemonapi.R;
import com.faysselyabahddou.demopokemonapi.model.Pokemon;

import java.util.ArrayList;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonArrayList;
    private Context context;

    public PokemonAdapter(ArrayList<Pokemon> pokemonArrayList, Context context) {
        this.pokemonArrayList = pokemonArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder pokemonViewHolder, int position) {

        Pokemon pokemon = pokemonArrayList.get(position);
        pokemonViewHolder.pokemonName.setText(pokemon.getName());

        /**
         * a simple way to get the image end point
         */
        int number = position + 1;
        String url = "http://pokeapi.co/media/sprites/pokemon/" + number + ".png";

        Glide.with(context)
                .load(url)
                .into(pokemonViewHolder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        private ImageView pokemonImage;
        private TextView pokemonName;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
