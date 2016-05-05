package cz.codecamp.mvp.di;

import javax.inject.Singleton;

import cz.codecamp.mvp.presenter.EarthQuakePresenter;
import cz.codecamp.mvp.presenter.MainPresenter;
import cz.codecamp.mvp.ui.activity.MainActivity;
import cz.codecamp.mvp.ui.fragment.EarthQuakeFragment;
import dagger.Component;

/**
 * Component for DI
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(EarthQuakeFragment earthQuakeFragment);

    void inject(EarthQuakePresenter earthQuakePresenter);

    void inject(MainPresenter mainPresenter);
}
