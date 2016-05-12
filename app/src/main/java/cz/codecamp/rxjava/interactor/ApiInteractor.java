package cz.codecamp.rxjava.interactor;

import android.support.annotation.NonNull;

import cz.codecamp.rxjava.domain.response.SearchUsersResponse;
import cz.codecamp.rxjava.domain.rest.ApiDescription;
import rx.Observable;

/**
 * Interactor providing data from the API
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class ApiInteractor implements IApiInteractor {
    public static final String TAG = ApiInteractor.class.getName();
    private final ApiDescription apiDescription;

    public ApiInteractor(ApiDescription apiDescription) {
        this.apiDescription = apiDescription;
    }

    @Override
    public Observable<SearchUsersResponse> obtainUsersBySearchQuery(@NonNull String text) {
        return apiDescription.obtainUsersBySearchQuery(text);
    }
}
