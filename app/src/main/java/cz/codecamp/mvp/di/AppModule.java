package cz.codecamp.mvp.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://earthquake.usgs.gov/fdsnws/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.networkInterceptors().add(new StethoInterceptor());
        return builder.build();
    }
}
