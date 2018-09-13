package com.faysselyabahddou.demopokemonapi.pokemon;

import com.faysselyabahddou.demopokemonapi.app.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
@Module
public class PokemonModule {

    private final PokemonContract.View view;

    public PokemonModule(PokemonContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    PokemonContract.View providePokemonContractView() {
        return view;
    }
}
