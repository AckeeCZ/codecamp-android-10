package cz.codecamp.rxjava.ui.base;

import android.app.Fragment;

import cz.codecamp.rxjava.mvp.presenter.base.Presenter;
import cz.codecamp.rxjava.mvp.view.base.IBaseView;

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isFinishing()) {
            getPresenter().onDestroy();
        }
    }
}
