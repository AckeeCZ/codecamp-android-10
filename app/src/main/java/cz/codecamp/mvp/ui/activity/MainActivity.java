package cz.codecamp.mvp.ui.activity;

import android.os.Bundle;

import javax.inject.Inject;

import cz.codecamp.mvp.App;
import cz.codecamp.mvp.R;
import cz.codecamp.mvp.presenter.MainPresenter;
import cz.codecamp.mvp.ui.base.PresenterActivity;
import cz.codecamp.mvp.ui.fragment.EarthQuakeFragment;
import cz.codecamp.mvp.view.IMainView;


public class MainActivity extends PresenterActivity<MainPresenter> implements IMainView {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getAppComponent().inject(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new EarthQuakeFragment()).commit();
    }

    @Override
    protected MainPresenter getPresenter() {
        return presenter;
    }
}
