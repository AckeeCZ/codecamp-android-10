package cz.codecamp.rxjava.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import cz.codecamp.rxjava.ApiConfig;
import cz.codecamp.rxjava.domain.rest.ApiDescription;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module providing api services
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
@Module
public class ServiceModule {
    public static final String TAG = ServiceModule.class.getName();

    @Provides
    @Singleton
    public ApiDescription provideApiDescription() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(generateClient())
                .build()
                .create(ApiDescription.class);
    }

    private OkHttpClient generateClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
