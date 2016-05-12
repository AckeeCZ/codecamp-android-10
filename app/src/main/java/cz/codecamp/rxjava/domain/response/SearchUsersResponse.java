package cz.codecamp.rxjava.domain.response;

import java.util.List;

import cz.codecamp.rxjava.domain.model.User;

/**
 * Response to {@code /search/users}
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class SearchUsersResponse {
    public static final String TAG = SearchUsersResponse.class.getName();

    List<User> items;

    public List<User> getItems() {
        return items;
    }
}
