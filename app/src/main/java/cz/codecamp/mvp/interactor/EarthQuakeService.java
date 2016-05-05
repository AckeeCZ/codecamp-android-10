package cz.codecamp.mvp.interactor;

import cz.codecamp.mvp.domain.QuakesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service to download earth quakes
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public interface EarthQuakeService {

    @GET("event/1/query?format=geojson")
    Call<QuakesResponse> listQuakes();
}
