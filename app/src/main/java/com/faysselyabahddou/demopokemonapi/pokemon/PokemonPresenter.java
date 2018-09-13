package com.faysselyabahddou.demopokemonapi.pokemon;

import android.app.Application;

import com.faysselyabahddou.demopokemonapi.R;
import com.faysselyabahddou.demopokemonapi.app.ServiceCreator;
import com.faysselyabahddou.demopokemonapi.model.Pokemon;
import com.faysselyabahddou.demopokemonapi.model.PokemonResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public class PokemonPresenter implements PokemonContract.Presenter {

    private Retrofit retrofit;
    private PokemonContract.View view;

    @Inject
    Application mApplication;

    @Inject
    public PokemonPresenter(Retrofit retrofit, PokemonContract.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }


    @Override
    public void getPokemon(boolean enableLoader, int offset) {
        view.onEnableLoader(true);
        retrofit.create(ServiceCreator.class)
                .getPokemon(20, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<PokemonResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(mApplication.getResources().getString(R.string.text_error), mApplication.getResources().getString(R.string.text_error_message_app));
                    }

                    @Override
                    public void onNext(Response<PokemonResponse> pokemonResponseResponse) {

                        view.onEnableLoader(false);
                        if (pokemonResponseResponse.isSuccessful()) {
                            PokemonResponse pokemonResponse = pokemonResponseResponse.body();
                            ArrayList<Pokemon> pokemonArrayList = pokemonResponse.getResults();
                            view.onResponseReceived(pokemonArrayList);
                        } else {
                            view.onError(mApplication.getResources().getString(R.string.text_error), mApplication.getResources().getString(R.string.text_error_message_app));

                        }
                    }
                });

    }
}
