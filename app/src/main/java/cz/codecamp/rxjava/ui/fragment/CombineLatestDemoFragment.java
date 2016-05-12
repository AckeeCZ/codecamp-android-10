package cz.codecamp.rxjava.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.codecamp.rxjava.R;
import rx.Observable;
import rx.functions.Func1;

/**
 * CombineLatest demo fragment
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
public class CombineLatestDemoFragment extends Fragment {
    public static final String TAG = CombineLatestDemoFragment.class.getName();

    @Bind(R.id.edit_combine_latest_name)
    EditText editCombineLatestName;
    @Bind(R.id.edit_combine_latest_surname)
    EditText editCombineLatestSurname;
    @Bind(R.id.edit_combine_latest_login)
    EditText editCombineLatestLogin;
    @Bind(R.id.edit_combine_latest_email)
    EditText editCombineLatestEmail;
    @Bind(R.id.btn_combine_latest_submit)
    Button btnCombineLatestSubmit;
    private Observable<Boolean> nameObservable;
    private Observable<Boolean> surnameObservable;
    private Observable<Boolean> emailObservable;
    private Observable<Boolean> loginObservable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combine_latest_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Func1<CharSequence, Boolean> isSequenceEmpty = charSequence -> !charSequence.toString().isEmpty();
        nameObservable = RxTextView.textChanges(editCombineLatestName).map(isSequenceEmpty);
        surnameObservable = RxTextView.textChanges(editCombineLatestSurname).map(isSequenceEmpty);
        emailObservable = RxTextView.textChanges(editCombineLatestEmail).map(isSequenceEmpty);
        loginObservable = RxTextView.textChanges(editCombineLatestLogin).map(isSequenceEmpty);
    }

    @Override
    public void onResume() {
        super.onResume();
        Observable.combineLatest(nameObservable, surnameObservable, emailObservable, loginObservable, (aBoolean, aBoolean2, aBoolean3, aBoolean4) -> {
            return aBoolean & aBoolean2 & aBoolean3 & aBoolean4;
        })
                .distinctUntilChanged()
                .subscribe(shouldBeEnabled -> {
                    btnCombineLatestSubmit.setEnabled(shouldBeEnabled);
                    Log.d(TAG, "onNext should be enabled = " + shouldBeEnabled);
                }, throwable -> {
                    throwable.printStackTrace();
                    Log.d(TAG, "onError");
                }, () -> Log.d(TAG, "onCompleted"));
    }

    @OnClick(R.id.btn_combine_latest_submit)
    public void onClick() {
        editCombineLatestName.getText().clear();
        editCombineLatestSurname.getText().clear();
        editCombineLatestLogin.getText().clear();
        editCombineLatestEmail.getText().clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
