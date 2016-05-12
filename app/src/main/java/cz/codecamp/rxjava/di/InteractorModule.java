package cz.codecamp.rxjava.di;

import javax.inject.Singleton;

import cz.codecamp.rxjava.domain.rest.ApiDescription;
import cz.codecamp.rxjava.interactor.ApiInteractor;
import cz.codecamp.rxjava.interactor.IApiInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Module for interactors module
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
@Module(includes = ServiceModule.class)
public class InteractorModule {
    public static final String TAG = InteractorModule.class.getName();

    @Provides
    @Singleton
    IApiInteractor provideApiInteractor(ApiDescription apiDescription) {
        return new ApiInteractor(apiDescription);
    }
}
