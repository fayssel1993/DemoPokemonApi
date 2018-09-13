package com.faysselyabahddou.demopokemonapi.pokemon;

import com.faysselyabahddou.demopokemonapi.model.Pokemon;

import java.util.List;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public interface PokemonContract {
    interface View {
        void onResponseReceived(List<Pokemon> response);

        void onEnableLoader(boolean enable);

        void onError(String title, String messages);

    }

    interface Presenter {
        void getPokemon(boolean enableLoader, int offset);
    }
}
