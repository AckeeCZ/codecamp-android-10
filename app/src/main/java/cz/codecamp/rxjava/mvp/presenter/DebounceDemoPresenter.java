package cz.codecamp.rxjava.mvp.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cz.codecamp.rxjava.App;
import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.domain.response.SearchUsersResponse;
import cz.codecamp.rxjava.interactor.IApiInteractor;
import cz.codecamp.rxjava.mvp.presenter.base.Presenter;
import cz.codecamp.rxjava.mvp.view.IDebounceDemoView;
import cz.codecamp.rxjava.ui.fragment.DebounceDemoFragment;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter for {@link DebounceDemoFragment}
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class DebounceDemoPresenter extends Presenter<IDebounceDemoView> {
    public static final String TAG = DebounceDemoPresenter.class.getName();

    @Inject
    IApiInteractor apiInteractor;

    private CompositeSubscription compositeSubscription;
    private List<User> users;

    @Override
    public void attachView(IDebounceDemoView view) {
        super.attachView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    public DebounceDemoPresenter() {
        compositeSubscription = new CompositeSubscription();
        App.getAppComponent().inject(this);
    }

    public void search(String text) {
        compositeSubscription.add(
                apiInteractor.obtainUsersBySearchQuery(text)
                        .subscribeOn(Schedulers.io())
                        .map(new Func1<SearchUsersResponse, List<User>>() {
                            @Override
                            public List<User> call(SearchUsersResponse searchUsersResponse) {
                                return searchUsersResponse.getItems();
                            }
                        })
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
                                DebounceDemoPresenter.this.users = users;
                            }
                        }));
    }

    public void searchLambda(String text) {
        compositeSubscription.add(
                apiInteractor.obtainUsersBySearchQuery(text)
                        .subscribeOn(Schedulers.io())
                        .map(SearchUsersResponse::getItems)
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
                                DebounceDemoPresenter.this.users = users;
                            }
                        }));
    }

    private void updateView() {
        if (getView() != null && users != null) {
            getView().setDataToView(users);
        }
    }
}
