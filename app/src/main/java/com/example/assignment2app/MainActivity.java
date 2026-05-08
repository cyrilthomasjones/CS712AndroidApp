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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button openSecondExplicitBtn, openSecondImplicitBtn, backgroundMonitorButton, btnBroadcast, btnImageActivity;
    MyBroadcastReceiver receiver;

    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;
    private static final int REQUEST_MSE712_PERMISSION = 2;
    private static final String CUSTOM_PERMISSION = "com.example.assignment2app.MSE712";

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        openSecondExplicitBtn = findViewById(R.id.openSecondExplicitBtn);
        openSecondImplicitBtn = findViewById(R.id.openSecondImplicitBtn);
        backgroundMonitorButton = findViewById(R.id.btnService);
        btnBroadcast = findViewById(R.id.btnBroadcast);
        btnImageActivity = findViewById(R.id.btnImageActivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assignment 7: request custom permission in onCreate
        requestMSE712PermissionIfNeeded();

        openSecondExplicitBtn.setOnClickListener(v -> {
            if (hasMSE712Permission()) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("launchSource", "Explicit Button");
                startActivity(intent);
            } else {
                Toast.makeText(this, "MSE712 permission not granted.", Toast.LENGTH_SHORT).show();
                requestMSE712PermissionIfNeeded();
            }
        });

        openSecondImplicitBtn.setOnClickListener(v -> {
            if (hasMSE712Permission()) {
                Intent intent = new Intent("com.example.assignment2app.OPEN_SECOND_SCREEN");
                intent.putExtra("launchSource", "Implicit Button");
                intent.setPackage(getPackageName());
                startActivity(intent);
            } else {
                Toast.makeText(this, "MSE712 permission not granted.", Toast.LENGTH_SHORT).show();
                requestMSE712PermissionIfNeeded();
            }
        });

        backgroundMonitorButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(this, MyForegroundService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(serviceIntent);
            else
                startService(serviceIntent);
        });

        btnBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent("com.cyril.MY_ACTION");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });

        btnImageActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.cyril.MY_ACTION");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED);
        else
            registerReceiver(receiver, filter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICATION_PERMISSION);
            }
        }
    }

    private boolean hasMSE712Permission() {
        return ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestMSE712PermissionIfNeeded() {
        if (ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{CUSTOM_PERMISSION},
                    REQUEST_MSE712_PERMISSION
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_MSE712_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "MSE712 permission granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "MSE712 permission denied.", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) unregisterReceiver(receiver);
    }
}