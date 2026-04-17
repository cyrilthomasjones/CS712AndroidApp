package com.example.assignment2app;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AssignmentUITest {

    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String PACKAGE_NAME = "com.example.assignment2app";

    private UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();
    }

    @Test
    public void launchApp_clickExplicitButton_verifySecondActivityContent() {
        Context context = ApplicationProvider.getApplicationContext();

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        assertNotNull("Launch intent for app is null", intent);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);

        // Handle runtime notification permission dialog if it appears
        if (device.wait(Until.hasObject(By.res("com.android.permissioncontroller:id/permission_allow_button")), 3000)) {
            device.findObject(By.res("com.android.permissioncontroller:id/permission_allow_button")).click();
        }

        // Wait for MainActivity button
        device.wait(Until.hasObject(By.text("Start Activity Explicitly")), LAUNCH_TIMEOUT);

        assertTrue(
                "Could not find 'Start Activity Explicitly' button",
                device.findObject(By.text("Start Activity Explicitly")) != null
        );

        device.findObject(By.text("Start Activity Explicitly")).click();

        // Verify content in SecondActivity
        device.wait(Until.hasObject(By.textContains("Device fragmentation")), LAUNCH_TIMEOUT);

        assertTrue(
                "Expected challenge text not found on SecondActivity",
                device.findObject(By.textContains("Device fragmentation")) != null
        );
    }
}