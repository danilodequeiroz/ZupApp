package com.brutcode.seedapp.di.component;

import com.brutcode.seedapp.di.module.OmdbApiModule;
import com.brutcode.seedapp.di.scope.UserScope;
import com.brutcode.seedapp.presenter.MainPresenter;
import com.brutcode.seedapp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Danilo on 04/07/2016.
 */
@UserScope // using the previously defined scope, note that @Singleton will not work
@Component(dependencies = NetComponent.class, modules = OmdbApiModule.class)
public interface OmdbApiComponent {
    void inject(MainActivity activity);
    void inject(MainPresenter presenter);
}
