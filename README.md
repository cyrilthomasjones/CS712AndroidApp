# Assignment2App

A simple Android application demonstrating activity navigation, camera integration, foreground services, and dynamic broadcast communication.

---

## Features

* Multi-activity Android application (Main, Second, Third Activity)
* Navigation using explicit and implicit intents
* Passing data between activities
* Foreground service with notification
* Dynamic broadcast receiver
* Camera integration (capture and display image)
* Image preview with visible border before and after capture
* Navigation back to main screen from camera activity
* Clean and user-friendly interface

---

## App Structure

### Main Activity

* Displays user information
* Launches the second activity using:

    * Explicit intent
    * Implicit intent (custom action string)
* Starts a foreground service
* Sends a custom internal broadcast
* Dynamically registers and unregisters a broadcast receiver
* Navigates to camera functionality (ThirdActivity)

---

### Second Activity

* Displays a list of mobile software engineering challenges
* Shows how the activity was launched (Explicit or Implicit)
* Provides navigation back to the main activity

---

### Third Activity (Camera Feature)

* Launches device camera using intent
* Captures image using system camera app
* Displays captured image inside an ImageView
* Shows a visible border around the image (before and after capture)
* Includes a button to return back to MainActivity
* Handles runtime camera permissions

---

### Foreground Service

* Runs as a foreground service
* Displays a persistent notification when started
* Implements notification channel and Android 14 foreground service requirements

---

### Broadcast Receiver

* Dynamically registered in `MainActivity`
* Listens for a custom internal action
* Displays a Toast message when a broadcast is received

---

## Technologies Used

* Java
* Android Studio
* Android SDK (API 24+)
* ConstraintLayout

---

## Mobile Engineering Challenges Covered

* Device fragmentation
* Battery optimization
* Security and privacy
* Network connectivity
* Performance optimization

---

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Build and run on an emulator or Android device

```
git clone <repository-url>
```

---

## Requirements

* Android Studio (latest version recommended)
* Android SDK API 24+
* Emulator or Android device (real device recommended for camera testing)

---

## Notes

* Camera functionality may behave differently on emulators depending on system configuration.
* A physical Android device is recommended for reliable camera testing.

---

## License

This project is for educational and demonstration purposes.
