package cz.codecamp.rxjava.interactor;

import android.support.annotation.NonNull;

import cz.codecamp.rxjava.domain.response.SearchUsersResponse;
import rx.Observable;

/**
 * Interface for api interactor
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public interface IApiInteractor {
    Observable<SearchUsersResponse> obtainUsersBySearchQuery(@NonNull String text);
}
