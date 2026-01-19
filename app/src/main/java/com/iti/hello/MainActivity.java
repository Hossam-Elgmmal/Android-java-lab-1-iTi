package com.iti.hello;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityX";
    public static final String NAME_KEY = "username";
    public static final String PHONE_KEY = "phone";

    private TextInputEditText username;
    private TextInputEditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        phone = findViewById(R.id.phoneNumber);

        findViewById(R.id.btnCancel)
                .setOnClickListener(v -> finish());
        findViewById(R.id.btnSubmit)
                .setOnClickListener(v -> getDataAndNavigate());
    }

    private void getDataAndNavigate() {
        Editable name = username.getText();
        Editable number = phone.getText();

        if (name == null || number == null) {
            Log.e(TAG, "onCreate: name and number are null");
            return;
        }
        boolean nameIsShort = name.toString().length() < 3;
        boolean numberIsShort = number.toString().length() < 11;

        if (nameIsShort) {
            username.setError("At least 3 letters");
        }
        if (numberIsShort) {
            phone.setError("enter a valid number with 11 digits");
        }
        if (!nameIsShort && !numberIsShort) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(NAME_KEY, name.toString());
            intent.putExtra(PHONE_KEY, number.toString());
            startActivity(intent);
        }
    }

}