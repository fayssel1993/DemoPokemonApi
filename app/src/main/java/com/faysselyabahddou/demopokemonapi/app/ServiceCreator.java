package com.faysselyabahddou.demopokemonapi.app;

import com.faysselyabahddou.demopokemonapi.model.PokemonResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public interface ServiceCreator {
    @GET("pokemon")
    Observable<Response<PokemonResponse>> getPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
