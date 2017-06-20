package pamo.eventsmanager.activities.mainActivities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import pamo.eventsmanager.R;
import pamo.eventsmanager.fragments.DetailFragment;
import pamo.eventsmanager.fragments.OverviewFragment;

public class MainActivity extends Activity implements
        OverviewFragment.OverviewFragmentActivityListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS EventsTable(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Title VARCHAR, Date VARCHAR, Time VARCHAR);");
        final Cursor cursor = db.rawQuery("SELECT * FROM EventsTable", null);
        String[] columns = new String[]{"Title"};
        int to[] = new int[]{R.id.list_item};

        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, columns, to);
        ListView listView = (ListView) findViewById(R.id.events_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemSelected(cursor.getString(cursor.getColumnIndex("Title")),
                        cursor.getString(cursor.getColumnIndex("Date")),
                        cursor.getString(cursor.getColumnIndex("Time")));
            }
        });
    }

    @Override
    public void onItemSelected(String title, String date, String time) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);

        if (fragment != null && fragment.isInLayout()) {
            fragment.setEventTitle(title);
            fragment.setEventDate(date);
            fragment.setEventTime(time);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            startActivity(intent);
        }
    }

    public void goAddEvent(View view){
        startActivity(new Intent(MainActivity.this, AddEventActivity.class));
    }
}