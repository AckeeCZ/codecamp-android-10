package cz.codecamp.rxjava.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Fragment with methods create, from and just
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
public class CreateObservableDemoFragment extends Fragment {
    public static final String TAG = CreateObservableDemoFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ----------------------- FROM ------------------------------------------------
        String[] stringsArray = new String[]{
                "Code",
                "Camp",
                "is",
                "the",
                "best",
                "best",
                "Camp",
                "ever"
        };

        Observable.from(stringsArray)
                .distinct()
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   Log.d("FROM", "OnNext " + s);
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   Log.d("FROM", "OnError");
                                   throwable.printStackTrace();
                               }
                           },
                        new Action0() {
                            @Override
                            public void call() {
                                Log.d("FROM", "OnCompleted");
                            }
                        });

        // ----------------------- JUST ------------------------------------------------
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                .distinct()
                .take(5)
                .subscribe(new Action1<Integer>() {
                               @Override
                               public void call(Integer number) {
                                   Log.d("JUST", "OnNext " + String.valueOf(number));
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   throwable.printStackTrace();
                                   Log.d("JUST", "OnError");
                               }
                           },
                        new Action0() {
                            @Override
                            public void call() {
                                Log.d("JUST", "OnCompleted");
                            }
                        });

        // ----------------------- CREATE ------------------------------------------------
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 0; i < stringsArray.length; i++) {
                            observer.onNext(stringsArray[i]);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String item) {
                Log.d("CREATE", "OnNext " + item);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
                Log.d("CREATE", "OnError");
            }

            @Override
            public void onCompleted() {
                Log.d("CREATE", "OnCompleted");
            }
        });
    }
}
