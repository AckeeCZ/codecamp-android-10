package cz.codecamp.mvp.domain;

/**
 * Properties for {@link Quake}
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class QuakeProperties {
    public static final String TAG = QuakeProperties.class.getName();

    float mag;

    String place;

    long time;

    public float getMag() {
        return mag;
    }

    public void setMag(float mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
