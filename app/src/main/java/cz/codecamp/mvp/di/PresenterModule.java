package cz.codecamp.mvp.di;

import javax.inject.Singleton;

import cz.codecamp.mvp.presenter.EarthQuakePresenter;
import cz.codecamp.mvp.presenter.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for presenter injection
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
@Module
public class PresenterModule {
    public static final String TAG = PresenterModule.class.getName();

    @Provides
    @Singleton
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    EarthQuakePresenter provideEarthQuakePresenter() {
        return new EarthQuakePresenter();
    }
}
