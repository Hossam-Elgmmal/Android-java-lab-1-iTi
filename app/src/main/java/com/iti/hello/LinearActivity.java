package com.iti.hello;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LinearActivity extends AppCompatActivity {

    private int counter = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.elephant).setOnClickListener(v -> {
            if (counter > 4) {
                Intent intent = new Intent(this, CounterActivity.class);
                startActivity(intent);
                finishAffinity();
                return;
            }
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this, "Click " + (5 - counter) + " more times", Toast.LENGTH_SHORT);
            toast.show();
            counter++;
        });

    }
}