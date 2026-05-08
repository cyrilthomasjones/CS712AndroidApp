# Assignment2App

A hands-on Android application built as part of Mobile Software Engineering coursework.  
This app demonstrates core Android concepts including activity navigation, camera usage, custom permissions, background services, and broadcast communication in a simple and practical way.

---

## 🚀 Features

- Multi-activity structure (Main, Second, and Camera Activity)
- Navigation using both explicit and implicit intents
- Passing data between activities
- Foreground service with persistent notification
- Dynamic broadcast receiver implementation
- Camera integration (capture and display image)
- Image preview with a visible border (before and after capture)
- Custom dangerous permission implementation
- Runtime permission request handling
- Protected exported activity using custom permission
- Navigation back to main screen from camera activity
- Clean and user-friendly interface

---

## 📱 App Overview

### 🔹 MainActivity
- Displays basic user information
- Launches SecondActivity using:
    - Explicit intent
    - Implicit intent (custom action string)
- Requests custom runtime permission (`MSE712`)
- Checks permission before opening protected activity
- Starts a foreground service
- Sends a custom internal broadcast
- Dynamically registers and unregisters a broadcast receiver
- Navigates to camera functionality (ThirdActivity)

---

### 🔹 SecondActivity
- Displays a list of mobile software engineering challenges
- Shows how the activity was launched (Explicit or Implicit)
- Exported and protected using a custom dangerous permission
- Provides navigation back to the main activity

---

### 🔹 ThirdActivity (Camera Feature)
- Launches device camera using intent
- Captures image using system camera app
- Displays captured image inside an ImageView
- Shows a visible border around the image (before and after capture)
- Includes a button to return back to MainActivity
- Handles runtime camera permissions

---

## 🔐 Custom Permission (Assignment 7)

The application implements a custom dangerous permission:

```xml
com.example.assignment2app.MSE712