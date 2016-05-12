package cz.codecamp.rxjava.mvp.presenter.base;

import android.util.Log;

import cz.codecamp.rxjava.mvp.view.base.IBaseView;

/**
 * Base Presenter class
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class Presenter<V extends IBaseView> {
    public static final String TAG = Presenter.class.getName();

    V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
        onTakeView(view);
    }

    public void detachView() {
        this.view = null;
        onViewDrop();
    }

    /**
     * Called when activity is finishing
     */
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * Called after view is connected to presenter
     *
     * @param view Connected view
     */
    protected void onTakeView(V view) {
        Log.d(TAG, "onTakeView() called with");
    }

    /**
     * Called after view is disconnected from presenter
     */
    protected void onViewDrop() {
        Log.d(TAG, "onViewDrop() called");
    }

}
