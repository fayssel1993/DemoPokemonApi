package com.faysselyabahddou.demopokemonapi.pokemon;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.faysselyabahddou.demopokemonapi.R;
import com.faysselyabahddou.demopokemonapi.app.App;
import com.faysselyabahddou.demopokemonapi.common.PopUpMessage;
import com.faysselyabahddou.demopokemonapi.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements PokemonContract.View {

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private ArrayList<Pokemon> pokemonArrayList;
    private ProgressBar loadMore;
    PopUpMessage mPopUpMessage;

    private int offset;

    private boolean isFinished;
    private boolean mIsLoading;

    @Inject
    Application mApplication;
    @Inject
    PokemonPresenter pokemonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPokemonComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .pokemonModule(new PokemonModule(this))
                .build()
                .inject(this);
        bind();

        pokemonArrayList = new ArrayList<>();
        adapter = new PokemonAdapter(pokemonArrayList, this);
        mPopUpMessage = new PopUpMessage(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int childCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (isFinished) {
                        if (childCount + firstVisibleItemPosition >= totalItemCount) {
                            mIsLoading = true;
                            offset += 20;
                            pokemonPresenter.getPokemon(true, offset);
                        }
                    }
                }
            }
        });

        offset = 0;
        isFinished = true;
        recyclerView.setLayoutManager(layoutManager);
        pokemonPresenter.getPokemon(true, offset);

    }

    private void bind() {
        recyclerView = findViewById(R.id.recycler_view);
        loadMore = findViewById(R.id.progressLodMore);
    }

    @Override
    public void onResponseReceived(List<Pokemon> response) {

        if (mIsLoading) {
            mIsLoading = false;
            loadMore.setVisibility(View.GONE);
        } else if (response.isEmpty()) {
            mIsLoading = true;
        }
        pokemonArrayList.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEnableLoader(boolean enable) {
        if (enable) {
            loadMore.setVisibility(View.VISIBLE);
        } else {
            loadMore.setVisibility(View.GONE);
        }

    }

    @Override
    public void onError(String title, String messages) {
        mPopUpMessage.showPopUp(title, messages);
    }
}
