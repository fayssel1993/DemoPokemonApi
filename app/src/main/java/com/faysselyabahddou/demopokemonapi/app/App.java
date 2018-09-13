package com.faysselyabahddou.demopokemonapi.app;

import android.app.Application;
import android.content.Context;

import com.faysselyabahddou.demopokemonapi.BuildConfig;

/**
 * Created by Fayssel Yabahddou on 9/12/18.
 */
public class App extends Application {
    private NetComponent mNetComponent;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASEURL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}

