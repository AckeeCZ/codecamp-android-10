package cz.codecamp.rxjava.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.codecamp.rxjava.App;
import cz.codecamp.rxjava.R;
import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.mvp.presenter.MergeDemoPresenter;
import cz.codecamp.rxjava.mvp.view.IMergeDemoView;
import cz.codecamp.rxjava.ui.adapter.UserAdapter;
import cz.codecamp.rxjava.ui.base.PresenterFragment;

/**
 * Merge demo fragment
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
public class MergeDemoFragment extends PresenterFragment<MergeDemoPresenter> implements IMergeDemoView {
    public static final String TAG = MergeDemoFragment.class.getName();

    @Bind(R.id.recycler_merge)
    RecyclerView recyclerMerge;

    @Inject
    MergeDemoPresenter presenter;

    public MergeDemoFragment() {
        App.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_merge_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerMerge.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setDataToView(@NonNull List<User> users) {
        UserAdapter adapter;
        if (recyclerMerge.getAdapter() != null) {
            adapter = (UserAdapter) recyclerMerge.getAdapter();
            adapter.setData(users);
        } else {
            adapter = new UserAdapter(users);
            recyclerMerge.setAdapter(adapter);
        }
    }

    @Override
    protected MergeDemoPresenter getPresenter() {
        return presenter;
    }
}
