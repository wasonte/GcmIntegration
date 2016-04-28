# GcmIntegration
Google Cloud Messaging solution with a test server GCM application (you can receive test push notifications without implementing a GCM server)

- With this approach you can register and unregister your app from gcm
- GCM server implementation not provided 
- Example of using unregister: You want an application that only receive push if the user is logged on the app.

If you want to test your app, run this script in Android Studio console to send a test push notification:
    gradlew run -Pmsg="Push notification text"
    
You can found GCM documentation here (how to obtain your configuration file and API KEY, ...)
    https://developers.google.com/cloud-messaging/android/start
