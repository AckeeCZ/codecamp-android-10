package cz.codecamp.rxjava.di;

import javax.inject.Singleton;

import cz.codecamp.rxjava.mvp.presenter.DebounceDemoPresenter;
import cz.codecamp.rxjava.mvp.presenter.MergeDemoPresenter;
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
    DebounceDemoPresenter provideBufferDemoPresenter() {
        return new DebounceDemoPresenter();
    }

    @Provides
    @Singleton
    MergeDemoPresenter provideMergeDemoPresenter() {
        return new MergeDemoPresenter();
    }
}
