package com.ansaralhojat.ansaralhojat;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MeetingsActivityFragmentActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        MeetingCustomAdapter.context = getApplicationContext();
        MeetingCustomAdapter.meetingsActivityFragmentActivity = this;
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            MeetingRecyclerViewFragment fragment = new MeetingRecyclerViewFragment();
            transaction.replace(R.id.meetings_content_fragment, fragment);
            transaction.commit();
        }
    }

}
