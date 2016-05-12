package cz.codecamp.rxjava.ui.base;

import android.support.v7.app.AppCompatActivity;

import cz.codecamp.rxjava.mvp.presenter.base.Presenter;
import cz.codecamp.rxjava.mvp.view.base.IBaseView;

/**
 * Base activity with presenter
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public abstract class PresenterActivity<P extends Presenter> extends AppCompatActivity {
    public static final String TAG = PresenterActivity.class.getName();

    protected abstract P getPresenter();

    @Override
    protected void onResume() {
        super.onResume();

        getPresenter().attachView((IBaseView) this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getPresenter().detachView();
    }
}
