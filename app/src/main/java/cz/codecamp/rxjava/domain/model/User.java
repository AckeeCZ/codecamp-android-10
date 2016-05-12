package cz.codecamp.rxjava.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * POJO User
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class User {
    public static final String TAG = User.class.getName();

    private String login;

    private long id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public User(String login, long id, String avatarUrl) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
