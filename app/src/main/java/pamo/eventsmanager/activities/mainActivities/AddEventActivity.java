package pamo.eventsmanager.activities.mainActivities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import pamo.eventsmanager.R;

public class AddEventActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS EventsTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR, Date VARCHAR, Time VARCHAR);");
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void addEvent(View view){
        EditText eventTitle = (EditText) findViewById(R.id.title_field);
        EditText dateField  = (EditText) findViewById(R.id.in_date);
        EditText timeField  = (EditText) findViewById(R.id.in_time);

        String title = eventTitle.getText().toString();
        String date  = dateField.getText().toString();
        String time  = timeField.getText().toString();
        if(title.length() <= 5){
            Toast toast = Toast.makeText(AddEventActivity.this, "Title must be of minimum 6 characters length.", Toast.LENGTH_SHORT);
            toast.show();
        }else if(date.length() < 8){
            Toast toast = Toast.makeText(AddEventActivity.this, "Date must be picked.", Toast.LENGTH_SHORT);
            toast.show();
        }else if(time.length() < 3){
            Toast toast = Toast.makeText(AddEventActivity.this, "Time must be picked.", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
            db.execSQL("INSERT INTO EventsTable(Title, Date, Time) VALUES('"+ title + "','" + date + "','" + time + "')");
            startActivity(new Intent(AddEventActivity.this, MainActivity.class));
        }
    }
}
