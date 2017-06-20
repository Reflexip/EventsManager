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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS UserDetails(Login VARCHAR, Password VARCHAR);");
    }

    public void registerNewUser(View view){
        EditText eTLoginField   = (EditText) findViewById(R.id.RegLoginField);
        EditText eTPassField    = (EditText) findViewById(R.id.RegPassField);
        EditText eTConfirmField = (EditText) findViewById(R.id.RegConfirmField);

        String login        = eTLoginField.getText().toString();
        String password     = eTPassField.getText().toString();
        String passConfirm  = eTConfirmField.getText().toString();

        if(loginAvailable(login)) {
            if (password.equals(passConfirm)) {
                SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
                db.execSQL("INSERT INTO UserDetails(Login, Password) VALUES('"
                        + login + "','" + password + "')");
                startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
            }else{
                passwordDoesntMatchDialog();
            }
        }else{
            loginAlreadyUsedDialog();
        }
    }

    private boolean loginAvailable(String login){
        SQLiteDatabase db = openOrCreateDatabase("EventsManagerDataBase", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails WHERE Login = ?", new String[] {login});
        return cursor.getCount() == 0;
    }

    private void loginAlreadyUsedDialog(){
        Toast info = Toast.makeText(RegisterActivity.this, "Login is already used.", Toast.LENGTH_LONG);
        info.show();
    }

    private void passwordDoesntMatchDialog(){
        Toast info = Toast.makeText(RegisterActivity.this, "Passwords don't match.", Toast.LENGTH_LONG);
        info.show();
    }
}
