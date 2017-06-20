package pamo.eventsmanager.activities.mainActivities;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pamo.eventsmanager.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS UserDetails(Login VARCHAR, Password VARCHAR);");
    }

    public void goSetPassword(View view) {
        startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
    }

    public void logIn(View view){
        EditText eTLoginField   = (EditText) findViewById(R.id.LoginField);
        EditText eTPassField    = (EditText) findViewById(R.id.PasswordField);

        String login        = eTLoginField.getText().toString();
        String password     = eTPassField.getText().toString();


        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails WHERE Login = ? AND Password = ?", new String[] {login, password});

        if(cursor != null && cursor.getCount() > 0){
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
        }else{
            Toast info = Toast.makeText(LogInActivity.this, "Wrong login or password", Toast.LENGTH_LONG);
            info.show();
        }

    }
}
