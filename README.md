# Assignment2App

A hands-on Android application built as part of Mobile Software Engineering coursework.  
This app demonstrates core Android concepts including activity navigation, camera usage, background services, and broadcast communication in a simple and practical way.

---

## 🚀 Features

- Multi-activity structure (Main, Second, and Camera Activity)
- Navigation using both explicit and implicit intents
- Passing data between activities
- Foreground service with persistent notification
- Dynamic broadcast receiver implementation
- Camera integration (capture and display image)
- Image preview with a visible border (before and after capture)
- Navigation back to main screen from camera activity
- Clean and user-friendly interface

---

## 📱 App Overview

### 🔹 MainActivity
- Displays basic user information
- Launches SecondActivity using:
    - Explicit intent
    - Implicit intent (custom action string)
- Starts a foreground service
- Sends a custom internal broadcast
- Dynamically registers and unregisters a broadcast receiver
- Navigates to camera functionality (ThirdActivity)

---

### 🔹 SecondActivity
- Displays a list of mobile software engineering challenges
- Shows how the activity was launched (Explicit or Implicit)
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

## ⚙️ Background Components

### 🔸 Foreground Service
- Runs as a foreground service
- Displays a persistent notification when started
- Implements notification channel and Android 14 foreground service requirements

---

### 🔸 Broadcast Receiver
- Dynamically registered in MainActivity
- Listens for a custom internal action
- Displays a Toast message when a broadcast is received

---

## 🧠 Mobile Engineering Challenges Covered

- Device fragmentation
- Battery optimization
- Security and privacy
- Network connectivity
- Performance optimization

---

## 🛠️ Technologies Used

- Java
- Android Studio
- Android SDK (API 24+)
- ConstraintLayout

---

## ▶️ Getting Started

1. Clone the repository:
   ```bash
   git clone <your-repository-url>