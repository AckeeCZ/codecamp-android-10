package cz.codecamp.rxjava.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Base app module
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
@Module(
        includes = {
                PresenterModule.class,
                InteractorModule.class
        }
)
public class AppModule {
    public static final String TAG = AppModule.class.getName();

    Context ctx;

    public AppModule(Context ctx) {
        this.ctx = ctx;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return ctx;
    }
}
