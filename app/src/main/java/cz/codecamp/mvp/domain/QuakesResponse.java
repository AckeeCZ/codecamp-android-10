package cz.codecamp.mvp.domain;

import com.facebook.stetho.json.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cz.codecamp.mvp.interactor.EarthQuakeService;

/**
 * Response from {@link EarthQuakeService#listQuakes()}
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class QuakesResponse {
    public static final String TAG = QuakesResponse.class.getName();


    String type;

    @SerializedName("features")
    List<Quake> quakes;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Quake> getQuakes() {
        return quakes;
    }

    public void setQuakes(List<Quake> quakes) {
        this.quakes = quakes;
    }
}
