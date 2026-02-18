# Assignment2App

A simple Android application demonstrating activity navigation, foreground services, and dynamic broadcast communication.

---

## Features

* Two-activity Android application
* Navigation using explicit intents
* Navigation using implicit intents (custom action)
* Passing data between activities
* Foreground service with notification
* Dynamic broadcast receiver
* Display of mobile software engineering challenges
* Clean and simple user interface

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

---

### Second Activity

* Displays a list of mobile software engineering challenges
* Shows how the activity was launched (Explicit or Implicit)
* Provides navigation back to the main activity

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
* Emulator or Android device

---

## License

This project is for educational and demonstration purposes.
