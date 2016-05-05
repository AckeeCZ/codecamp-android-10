package cz.codecamp.mvp.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import cz.codecamp.mvp.App;
import cz.codecamp.mvp.domain.Quake;
import cz.codecamp.mvp.domain.QuakesResponse;
import cz.codecamp.mvp.interactor.EarthQuakeService;
import cz.codecamp.mvp.presenter.base.Presenter;
import cz.codecamp.mvp.view.IEarthQuakeView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * EarthQuake presenter
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class EarthQuakePresenter extends Presenter<IEarthQuakeView> {
    public static final String TAG = EarthQuakePresenter.class.getName();

    List<Quake> quakeList;

    @Inject
    EarthQuakeService earthQuakeService;

    public EarthQuakePresenter() {
        App.getAppComponent().inject(this);

        new QuakeDownloadTask().execute();
    }

    @Override
    protected void onTakeView(IEarthQuakeView view) {
        super.onTakeView(view);
        updateView();
    }

    private void updateView() {
        if (getView() != null) {
            getView().showData(quakeList);
        }
    }

    class QuakeDownloadTask extends AsyncTask<Void, Void, List<Quake>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute() called with: DOWNLOADING DATA");
        }

        @Override
        protected List<Quake> doInBackground(Void... params) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Call<QuakesResponse> call = earthQuakeService.listQuakes();
            try {
                Response<QuakesResponse> response = call.execute();
                QuakesResponse quakesResponse = response.body();
                return quakesResponse.getQuakes();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Quake> quakes) {
            super.onPostExecute(quakes);
            quakeList = quakes;
            updateView();
        }
    }
}
