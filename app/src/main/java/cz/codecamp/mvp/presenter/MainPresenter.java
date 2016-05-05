package cz.codecamp.mvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import cz.codecamp.mvp.App;
import cz.codecamp.mvp.presenter.base.Presenter;
import cz.codecamp.mvp.view.IMainView;

/**
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class MainPresenter extends Presenter<IMainView> {
    public static final String TAG = MainPresenter.class.getName();
    private static final int DELAY = 10000; // 10 s

    @Inject
    Context ctx;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(ctx, "Hello", Toast.LENGTH_SHORT).show();
            handler.postDelayed(runnable, DELAY);
        }
    };


    public MainPresenter() {
        Log.d(TAG, "MainPresenter() called with: " + "");

        App.getAppComponent().inject(this);

        handler.postDelayed(runnable, DELAY);
    }

    @Override
    protected void onTakeView(IMainView view) {
        super.onTakeView(view);

        Log.d(TAG, "onTakeView() called with: " + "view = [" + view + "]");
    }

    @Override
    protected void onViewDrop() {
        super.onViewDrop();
        Log.d(TAG, "onViewDrop() called with: " + "");
//        handler.removeCallbacks(runnable);
    }
}
