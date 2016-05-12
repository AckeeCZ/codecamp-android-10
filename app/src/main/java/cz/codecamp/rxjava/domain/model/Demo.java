package cz.codecamp.rxjava.domain.model;

import rx.Observable;

/**
 * TODO add class description
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class Demo {
    public static final String TAG = Demo.class.getName();

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void test() {

        // Imperativni pristup

        String[] strings = new String[]{
                "prvni", "druhy", "treti", "ctvrty", "paty"
        };

        int totalLength = 0;
        for (int i = 0; i < strings.length; ++i) {
            totalLength += strings[i].length();
        }

        //--------------------------------------------------------------------

        // Funkcionalni pristup
        Observable.from(strings)
                .map(String::length)
                .reduce((integer, integer2) -> integer + integer2)
                .subscribe(sum -> {
                    // ....
                });
    }
}
