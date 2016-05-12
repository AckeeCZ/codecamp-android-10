package cz.codecamp.rxjava;

import android.app.Application;

import cz.codecamp.rxjava.di.AppComponent;
import cz.codecamp.rxjava.di.AppModule;
import cz.codecamp.rxjava.di.DaggerAppComponent;

/**
 * Application class for app
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class App extends Application {
    public static final String TAG = App.class.getName();

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
