package pamo.eventsmanager.activities.mainActivities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import pamo.eventsmanager.R;
import pamo.eventsmanager.fragments.DetailFragment;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String date = extras.getString("date");
            String time = extras.getString("time");
            DetailFragment detailFragment = (DetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);

            detailFragment.setEventTitle(title);
            detailFragment.setEventDate(date);
            detailFragment.setEventTime(time);
        }
    }
}