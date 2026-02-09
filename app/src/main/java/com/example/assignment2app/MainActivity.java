package com.example.assignment2app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnExplicit, btnImplicit;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Display setup
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            // Button initialization
            btnExplicit = findViewById(R.id.btnExplicit);
            btnImplicit = findViewById(R.id.btnImplicit);

            // Explicit Intent (Open SecondActivity directly)
            btnExplicit.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            });

            // Implicit Intent (Open SecondActivity via action string)
            btnImplicit.setOnClickListener(v -> {
                Intent intent = new Intent("com.example.assignment2app.SECOND_ACTIVITY");
                startActivity(intent);
            });
        }
}