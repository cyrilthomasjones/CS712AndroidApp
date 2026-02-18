package com.example.assignment2app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    Button openSecondExplicitBtn, openSecondImplicitBtn, backgroundMonitorButton, btnBroadcast;
    MyBroadcastReceiver receiver;

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI setup
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Buttons
        openSecondExplicitBtn = findViewById(R.id.openSecondExplicitBtn);
        openSecondImplicitBtn = findViewById(R.id.openSecondImplicitBtn);
        backgroundMonitorButton = findViewById(R.id.btnService);
        btnBroadcast = findViewById(R.id.btnBroadcast);

        // Insets safe area
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Explicit activity
        openSecondExplicitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("launchSource", "Explicit Button");
            startActivity(intent);
        });

        // Implicit activity
        openSecondImplicitBtn.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.assignment2app.OPEN_SECOND_SCREEN");
            intent.putExtra("launchSource", "Implicit Button");
            startActivity(intent);
        });

        // Start foreground service
        backgroundMonitorButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(this, MyForegroundService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(serviceIntent);
            else
                startService(serviceIntent);
        });

        // Send internal broadcast (restricted to this app)
        btnBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent("com.cyril.MY_ACTION");
            intent.setPackage(getPackageName()); // REQUIRED for Android 13+
            sendBroadcast(intent);
        });

        // Dynamic Broadcast Receiver
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.cyril.MY_ACTION");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED);
        else
            registerReceiver(receiver, filter);

        // Notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) unregisterReceiver(receiver);
    }
}