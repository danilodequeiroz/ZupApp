package com.brutcode.seedapp;

import android.app.Application;

import com.brutcode.seedapp.di.component.DaggerNetComponent;
import com.brutcode.seedapp.di.component.DaggerOmdbApiComponent;
import com.brutcode.seedapp.di.component.NetComponent;
import com.brutcode.seedapp.di.component.OmdbApiComponent;
import com.brutcode.seedapp.di.module.AppModule;
import com.brutcode.seedapp.di.module.NetModule;
import com.brutcode.seedapp.di.module.OmdbApiModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Danilo on 24/06/2016.
 */
public class MyApp extends Application {

    private NetComponent mNetComponent;
    private OmdbApiComponent mOmdbApiComponent;

    private AppModule mAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        MySnappyDb.getInstance(this);
        mAppModule = new AppModule(this);
        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(mAppModule) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("http://www.omdbapi.com/?"))
                .build();
        mOmdbApiComponent = DaggerOmdbApiComponent.builder()
                .netComponent(mNetComponent)
                .omdbApiModule(new OmdbApiModule())
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public OmdbApiComponent getOmdbComponent() {
        return mOmdbApiComponent;
    }

}
