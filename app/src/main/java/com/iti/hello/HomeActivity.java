package com.iti.hello;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.iti.hello.data.SourceFactory;
import com.iti.hello.data.SourceFactory.SourceType;
import com.iti.hello.data.UserSource;
import com.iti.hello.database.User;

public class HomeActivity extends AppCompatActivity {

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

        String name = getIntent().getStringExtra(Constants.NAME_KEY);
        String number = getIntent().getStringExtra(Constants.PHONE_KEY);

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(Constants.NAME_KEY);
            number = savedInstanceState.getString(Constants.PHONE_KEY);
        }

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.NAME_KEY, usernameTextView.getText().toString());
        outState.putString(Constants.PHONE_KEY, phoneTextView.getText().toString());
    }

    private void addToSharedPreferences() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();
        UserSource source = SourceFactory.getDataSource(this, SourceType.SHARED_PREFERENCES);
        source.saveUser(new User(1, name, number));
        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromSharedPreferences() {
        UserSource source = SourceFactory.getDataSource(this, SourceType.SHARED_PREFERENCES);
        User user = source.getUser();

        if (user != null) {
            usernameTextView.setText(user.getUsername());
            phoneTextView.setText(user.getPhone());
        }
    }

    private void addToInternalMemory() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();
        UserSource source = SourceFactory.getDataSource(this, SourceType.INTERNAL_MEMORY);
        source.saveUser(new User(1, name, number));

        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromInternalMemory() {
        UserSource source = SourceFactory.getDataSource(this, SourceType.INTERNAL_MEMORY);
        User user = source.getUser();

        if (user != null) {
            usernameTextView.setText(user.getUsername());
            phoneTextView.setText(user.getPhone());
        }
    }

    private void addToSqlite() {
        String name = usernameTextView.getText().toString();
        String number = phoneTextView.getText().toString();

        User user = new User(1, name, number);

        UserSource source = SourceFactory.getDataSource(this, SourceType.SQLITE);
        source.saveUser(user);

        usernameTextView.setText("");
        phoneTextView.setText("");
    }

    private void getFromSqlite() {
        UserSource source = SourceFactory.getDataSource(this, SourceType.SQLITE);
        User user = source.getUser();

        if (user != null) {
            usernameTextView.setText(user.getUsername());
            phoneTextView.setText(user.getPhone());
        }
    }
}