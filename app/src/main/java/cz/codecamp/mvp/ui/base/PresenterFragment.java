package cz.codecamp.mvp.ui.base;

import android.support.v4.app.Fragment;

import cz.codecamp.mvp.presenter.base.Presenter;
import cz.codecamp.mvp.view.base.IBaseView;

/**
 * Fragment with presenter
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public abstract class PresenterFragment<P extends Presenter> extends Fragment {
    public static final String TAG = PresenterFragment.class.getName();

    protected abstract P getPresenter();

    @Override
    public void onResume() {
        super.onResume();

        getPresenter().attachView((IBaseView) this);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPresenter().detachView();
    }
}
