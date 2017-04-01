package com.ansaralhojat.ansaralhojat;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class LecturesActivityFragmentActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            transaction.replace(R.id.lectures_content_fragment, fragment);
            transaction.commit();
        }
    }

}
