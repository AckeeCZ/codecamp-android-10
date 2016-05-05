package cz.codecamp.mvp.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import cz.codecamp.mvp.App;
import cz.codecamp.mvp.R;
import cz.codecamp.mvp.domain.Quake;
import cz.codecamp.mvp.presenter.EarthQuakePresenter;
import cz.codecamp.mvp.ui.adapter.EarthQuakeAdapter;
import cz.codecamp.mvp.ui.base.PresenterFragment;
import cz.codecamp.mvp.view.IEarthQuakeView;

/**
 * EarthQuake fragment for list of data
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public class EarthQuakeFragment extends PresenterFragment<EarthQuakePresenter> implements IEarthQuakeView {
    public static final String TAG = EarthQuakeFragment.class.getName();

    @Inject
    EarthQuakePresenter presenter;

    EarthQuakeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);

        adapter = new EarthQuakeAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected EarthQuakePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showData(List<Quake> quakeList) {
        adapter.setData(quakeList);
    }
}
