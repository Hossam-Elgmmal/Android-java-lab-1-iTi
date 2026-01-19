package com.iti.hello;

import static com.iti.hello.MainActivity.NAME_KEY;
import static com.iti.hello.MainActivity.PHONE_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity {

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

        TextView username = findViewById(R.id.username);
        TextView phone = findViewById(R.id.phoneNumber);

        if (name != null) {
            username.setText(name);
        }
        if (number != null) {
            phone.setText(number);
        }

        ((Toolbar) findViewById(R.id.topBar)).setNavigationOnClickListener(v -> finish());

        findViewById(R.id.btnNext)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(this, LinearActivity.class);
                    startActivity(intent);
                });

    }
}