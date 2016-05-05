package cz.codecamp.mvp.domain;

/**
 * Quake entity
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class Quake {
    public static final String TAG = Quake.class.getName();

    String type;

    QuakeProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public QuakeProperties getProperties() {
        return properties;
    }

    public void setProperties(QuakeProperties properties) {
        this.properties = properties;
    }
}
