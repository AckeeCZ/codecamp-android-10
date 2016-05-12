package cz.codecamp.rxjava.di;

import javax.inject.Singleton;

import cz.codecamp.rxjava.mvp.presenter.DebounceDemoPresenter;
import cz.codecamp.rxjava.mvp.presenter.MergeDemoPresenter;
import cz.codecamp.rxjava.ui.activity.MainActivity;
import cz.codecamp.rxjava.ui.fragment.DebounceDemoFragment;
import cz.codecamp.rxjava.ui.fragment.MergeDemoFragment;
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

    void inject(DebounceDemoFragment bufferInAutoCompleteFragment);

    void inject(DebounceDemoPresenter debounceDemoPresenter);

    void inject(MergeDemoPresenter mergeDemoPresenter);

    void inject(MergeDemoFragment mergeDemoFragment);
}
