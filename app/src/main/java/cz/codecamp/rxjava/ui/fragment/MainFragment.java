package cz.codecamp.rxjava.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.codecamp.rxjava.R;
import cz.codecamp.rxjava.ui.activity.MainActivity;

/**
 * Main fragment
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getName();

    public static Fragment createInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_create_observables)
    public void onCreateClick() {
        ((MainActivity) getActivity()).replaceFragment(new CreateObservableDemoFragment(), CreateObservableDemoFragment.TAG, true);
    }

    @OnClick(R.id.btn_debounce)
    public void onDebounceClick() {
        ((MainActivity) getActivity()).replaceFragment(new DebounceDemoFragment(), DebounceDemoFragment.TAG, true);
    }

    @OnClick(R.id.btn_math_observables)
    public void onMathClick() {
        ((MainActivity) getActivity()).replaceFragment(new MathDemoFragment(), MathDemoFragment.TAG, true);
    }

    @OnClick(R.id.btn_combine_latest)
    public void onCombineLatestClick() {
        ((MainActivity) getActivity()).replaceFragment(new CombineLatestDemoFragment(), CombineLatestDemoFragment.TAG, true);
    }

    @OnClick(R.id.btn_merge)
    public void onMergeClick() {
        ((MainActivity) getActivity()).replaceFragment(new MergeDemoFragment(), MergeDemoFragment.TAG, true);
    }
}
