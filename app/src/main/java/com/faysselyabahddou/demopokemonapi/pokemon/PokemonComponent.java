package com.faysselyabahddou.demopokemonapi.pokemon;

import com.faysselyabahddou.demopokemonapi.app.CustomScope;
import com.faysselyabahddou.demopokemonapi.app.NetComponent;

import dagger.Component;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = PokemonModule.class)
public interface PokemonComponent {
    void inject(MainActivity activity);

}
