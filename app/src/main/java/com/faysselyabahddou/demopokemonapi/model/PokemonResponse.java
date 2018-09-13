package com.faysselyabahddou.demopokemonapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public class PokemonResponse {

    @SerializedName("results")
    @Expose
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
