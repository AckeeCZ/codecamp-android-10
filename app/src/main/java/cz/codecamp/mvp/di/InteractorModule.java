package cz.codecamp.mvp.di;

import javax.inject.Singleton;

import cz.codecamp.mvp.interactor.EarthQuakeService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Module for interactors module
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
@Module
public class InteractorModule {
    public static final String TAG = InteractorModule.class.getName();

    @Provides
    @Singleton
    EarthQuakeService provideEarthQuakeService(Retrofit retrofit) {
        return retrofit.create(EarthQuakeService.class);
    }
}
