package com.iti.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    public static final String PREFERENCES_NAME = "my_preferences";

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



        usernameTextView.setText("");
        phoneTextView.setText("");
    }
    private void getFromInternalMemory() {

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