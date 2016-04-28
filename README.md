# GcmIntegration
Google Cloud Messaging solution with a test application (you can receive test push notifications)

- With this approach you can register and unregister your app from gcm
- Example of using unregister: You want an application that only receive push if the user is logged on the app.

You have to run this script in Android Studio console to send a test push notification:
    gradlew run -Pmsg="<message>"
