package com.iti.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "HomeActivityX";

    public static final String PREFERENCES_NAME = "my_preferences";
    public static final String INTERNAL_FILE_NAME = "my_file";

    public static final String NAME_KEY = "username";
    public static final String PHONE_KEY = "phone";

    private TextView usernameTextView;
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = getIntent().getStringExtra(NAME_KEY);
        String number = getIntent().getStringExtra(PHONE_KEY);

        usernameTextView = findViewById(R.id.username);
        phoneTextView = findViewById(R.id.phoneNumber);

        if (name != null) {
            usernameTextView.setText(name);
        }
        if (number != null) {
            phoneTextView.setText(number);
        }

        ((Toolbar) findViewById(R.id.topBar)).setNavigationOnClickListener(v -> finish());

        findViewById(R.id.btnNext)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(this, LinearActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.addToSharedPreferencesBtn)
                .setOnClickListener(v -> addToSharedPreferences());
        findViewById(R.id.getFromSharedPreferencesBtn)
                .setOnClickListener(v -> getFromSharedPreferences());
        findViewById(R.id.addToInternalMemoryBtn)
                .setOnClickListener(v -> addToInternalMemory());
        findViewById(R.id.getFromInternalMemoryBtn)
                .setOnClickListener(v -> getFromInternalMemory());
        findViewById(R.id.addToSqliteBtn)
                .setOnClickListener(v -> addToSqlite());
        findViewById(R.id.getFromSqliteBtn)
                .setOnClickListener(v -> getFromSqlite());

    }

    private void addToSharedPreferences() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();
        SharedPreferences sharedPrefs = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(PHONE_KEY, number);
        editor.apply();
        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromSharedPreferences() {
        SharedPreferences sharedPrefs = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String name = sharedPrefs.getString(NAME_KEY, "");
        String number = sharedPrefs.getString(PHONE_KEY, "");
        usernameTextView.setText(name);
        phoneTextView.setText(number);
    }

    private void addToInternalMemory() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(NAME_KEY, name);
            jsonObject.put(PHONE_KEY, number);
        } catch (JSONException e) {
            Log.e(TAG, "addToInternalMemory: unable to make json", e);
            return;
        }
        try (FileOutputStream output = openFileOutput(INTERNAL_FILE_NAME, MODE_PRIVATE)) {

            output.write(jsonObject.toString().getBytes());

        } catch (IOException e) {
            Log.e(TAG, "addToInternalMemory: unable to write to file", e);
            return;
        }
        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromInternalMemory() {
        try (FileInputStream input = openFileInput(INTERNAL_FILE_NAME)) {

            int length = input.available();
            byte[] bytes = new byte[length];
            input.read(bytes);

            String json = new String(bytes);

            JSONObject jsonObject = new JSONObject(json);

            String name = jsonObject.getString(NAME_KEY);
            String number = jsonObject.getString(PHONE_KEY);

            usernameTextView.setText(name);
            phoneTextView.setText(number);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "getFromInternalMemory: file not found", e);
        } catch (IOException e) {
            Log.e(TAG, "getFromInternalMemory: io exception", e);
        } catch (JSONException e) {
            Log.e(TAG, "getFromInternalMemory: unable to parse json", e);
        }
    }

    private void addToSqlite() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();


        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromSqlite() {

    }
}