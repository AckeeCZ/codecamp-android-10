package cz.codecamp.rxjava.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.codecamp.rxjava.App;
import cz.codecamp.rxjava.R;
import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.mvp.presenter.DebounceDemoPresenter;
import cz.codecamp.rxjava.mvp.view.IDebounceDemoView;
import cz.codecamp.rxjava.ui.adapter.UserAdapter;
import cz.codecamp.rxjava.ui.base.PresenterFragment;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Fragment with Buffer demo
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class DebounceDemoFragment extends PresenterFragment<DebounceDemoPresenter> implements IDebounceDemoView {
    public static final String TAG = DebounceDemoFragment.class.getName();

    @Inject
    DebounceDemoPresenter presenter;

    @Bind(R.id.edit_search)
    EditText editSearch;
    @Bind(R.id.text_search)
    TextView textSearch;
    @Bind(R.id.recycler_debounce)
    RecyclerView recyclerDebounce;
    private Subscription subscription;

    public DebounceDemoFragment() {
        App.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debounce_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerDebounce.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = RxTextView.textChangeEvents(editSearch)//
                .debounce(400, TimeUnit.MILLISECONDS)// default Scheduler is Computation
                .filter(changes -> !TextUtils.isEmpty(changes.text()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(change -> textSearch.setText(change.text()))
                .subscribe(new Subscriber<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted() called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        Log.d(TAG, "onNext() called " + textViewTextChangeEvent.text());
                        getPresenter().search(textViewTextChangeEvent.text().toString());
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setDataToView(@NonNull List<User> users) {
        UserAdapter adapter;
        if (recyclerDebounce.getAdapter() != null) {
            adapter = (UserAdapter) recyclerDebounce.getAdapter();
            adapter.setData(users);
        } else {
            adapter = new UserAdapter(users);
            recyclerDebounce.setAdapter(adapter);
        }
    }

    @Override
    protected DebounceDemoPresenter getPresenter() {
        return presenter;
    }
}
