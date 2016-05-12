package cz.codecamp.rxjava.domain.rest;

import cz.codecamp.rxjava.Constants;
import cz.codecamp.rxjava.domain.response.SearchUsersResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface for REST API
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {14. 4. 2016}
 */
public interface ApiDescription {

    @GET("search/users")
    Observable<SearchUsersResponse> obtainUsersBySearchQuery(@Query(Constants.QUERY_KEY) String text);
}
