# spindl-android

- To use: put the Spindl API key in the global `gradle.properties` file in a variable named `SPINDL_API_KEY`.

## Internal Setup for Spindl Developers

### Source code, making builds

1. Clone this repository.
2. Download and install [Android Studio](https://developer.android.com/studio).
3. Get the `spindl-upload-keystore.jks` from Linear or Notion and put it somewhere accessible but outside of the source repository.
4. In Android Studio's menu bar, open the bundle signing setup dialog via `Build > Generate Signed Bundle/APK...`.
5. In the bundle signing setup dialog, click the `Choose Existing...` button, and navigate to the `spindl-upload-keystore.jks` file.
6. Fill in the key store password (see the file in 3).
7. Choose the `upload` key alias.
8. Fill in the key password (from the file in 3).
9. Continue through the wizard to export a signed build (when necessary).

### Google Play Console

For sending out test builds, upload one of the signed AABs in the [Google Play Console](https://play.google.com/console). 

See [this help article](https://support.google.com/googleplay/android-developer/answer/9859348?visit_id=638333761849257711-3780210515&rd=1) for steps to make a new release.
[These instructions](https://support.google.com/googleplay/android-developer/answer/9842756) may also be handy for managing signing.



