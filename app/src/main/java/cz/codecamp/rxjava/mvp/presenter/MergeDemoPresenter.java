package cz.codecamp.rxjava.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cz.codecamp.rxjava.App;
import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.domain.response.SearchUsersResponse;
import cz.codecamp.rxjava.interactor.IApiInteractor;
import cz.codecamp.rxjava.mvp.presenter.base.Presenter;
import cz.codecamp.rxjava.mvp.view.IMergeDemoView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter for {@link cz.codecamp.rxjava.ui.fragment.MergeDemoFragment}
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class MergeDemoPresenter extends Presenter<IMergeDemoView> {
    public static final String TAG = MergeDemoPresenter.class.getName();

    @Inject
    IApiInteractor apiInteractor;

    private CompositeSubscription compositeSubscription;
    private List<User> cachedUsers;
    private List<User> allUsers;

    public MergeDemoPresenter() {
        App.getAppComponent().inject(this);
        cachedUsers = new ArrayList<>();
        cachedUsers.add(new User("Prvni cashovany user", 1, "http://lorempixel.com/400/200"));
        cachedUsers.add(new User("Druhy cashovany user", 2, "http://lorempixel.com/400/200"));
        cachedUsers.add(new User("Treti cashovany user", 3, "http://lorempixel.com/400/200"));
        cachedUsers.add(new User("Ctvrty cashovany user", 4, "http://lorempixel.com/400/200"));
        cachedUsers.add(new User("Paty cashovany user", 5, "http://lorempixel.com/400/200"));
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.unsubscribe();
    }

    @Override
    public void attachView(IMergeDemoView view) {
        super.attachView(view);
        compositeSubscription = new CompositeSubscription();
        allUsers = new ArrayList<>();
        search("Jake Wharton");
    }

    public void search(String text) {
        Observable<List<User>> apiObservable = apiInteractor.obtainUsersBySearchQuery(text)
                .map(SearchUsersResponse::getItems);

        Observable<List<User>> cacheObservable = Observable.just(cachedUsers);

        compositeSubscription.add(
                Observable.merge(apiObservable, cacheObservable)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<User>>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "Completed!");
                                updateView();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "Ho ho ho error!");
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(List<User> users) {
                                Log.i(TAG, "OnNext!");
                                allUsers.addAll(users);
                            }
                        }));
    }

    private void updateView() {
        if (getView() != null) {
            getView().setDataToView(allUsers);
        }
    }
}
