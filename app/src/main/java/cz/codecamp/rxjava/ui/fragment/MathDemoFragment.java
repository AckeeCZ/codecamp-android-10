package cz.codecamp.rxjava.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.codecamp.rxjava.R;
import rx.Observable;
import rx.observables.MathObservable;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * {@link MathObservable#min(Observable), MathObservable#max(Observable), MathObservable#sumInteger(Observable)}
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
public class MathDemoFragment extends Fragment {
    public static final String TAG = MathDemoFragment.class.getName();

    @Bind(R.id.edit_minmax)
    EditText editMinmax;
    @Bind(R.id.text_minmax_min)
    TextView textMinmaxMin;
    @Bind(R.id.text_minmax_max)
    TextView textMinmaxMax;
    @Bind(R.id.text_minmax_sum)
    TextView textMinmaxSum;
    @Bind(R.id.frame_minmax_content)
    LinearLayout frameMinmaxContent;
    @Bind(R.id.text_minmax_error)
    TextView textMinmaxError;
    @Bind(R.id.frame_minmax_error)
    RelativeLayout frameMinmaxError;
    @Bind(R.id.text_minmax_current_sum)
    TextView textMinmaxCurrentSum;

    private CompositeSubscription subscription;
    private final PublishSubject<Integer> numberSubject;

    public MathDemoFragment() {
        numberSubject = PublishSubject.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = new CompositeSubscription();

        // MathObservable.min
        subscription.add(MathObservable.min(numberSubject).subscribe(integer -> {
            textMinmaxMin.setText(getString(R.string.minmax_min_value, integer));
        }, this::onError));

        // MathObservable.max
        subscription.add(MathObservable.max(numberSubject).subscribe(integer -> {
            textMinmaxMax.setText(getString(R.string.minmax_max_value, integer));
        }, this::onError));

        // MathObservable.sumInteger
        subscription.add(MathObservable.sumInteger(numberSubject).subscribe(integer -> {
            textMinmaxSum.setText(getString(R.string.minmax_sum_value, integer));
        }, this::onError));

        // Observable.scan
        subscription.add(numberSubject.scan((integer, integer2) -> integer + integer2)
                .subscribe(integer -> {
                    textMinmaxCurrentSum.setText(getString(R.string.minmax_current_sum_value, integer));
                }, this::onError));
    }

    private void onError(Throwable throwable) {
        frameMinmaxContent.setVisibility(View.GONE);
        frameMinmaxError.setVisibility(View.VISIBLE);

        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        textMinmaxError.setText(errors.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    @OnClick(R.id.btn_minmax_submit)
    public void onSubmitClick() {
        try {
            int value = Integer.parseInt(editMinmax.getText().toString());
            numberSubject.onNext(value);
        } catch (NumberFormatException exception) {
            numberSubject.onError(exception);
        }
        editMinmax.getText().clear();
    }

    @OnClick(R.id.btn_minmax_complete)
    public void onCompleteClick() {
        numberSubject.onCompleted();
    }
}
