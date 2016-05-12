package cz.codecamp.rxjava.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cz.codecamp.rxjava.R;
import cz.codecamp.rxjava.ui.fragment.MainFragment;


public class MainActivity extends AppCompatActivity {

    public static final int CONTENT_VIEW_ID = R.id.fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getFragmentManager().beginTransaction()
                .add(CONTENT_VIEW_ID, MainFragment.createInstance(), MainFragment.TAG)
                .addToBackStack(MainFragment.TAG)
                .commit();
    }

    public void replaceFragment(Fragment fragment, String name, boolean addToBackStack) {
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(CONTENT_VIEW_ID, fragment, fragment.getClass().getName());
            if (addToBackStack) {
                transaction.addToBackStack(name);
            }

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        } catch (Exception e) {//java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
