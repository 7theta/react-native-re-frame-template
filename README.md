# react-native-re-frame

A Leiningen template for building React Native apps with shadow-cljs, reagent, and re-frame.

## Project Setup

To create a project based on the template, execute the `lein new react-native-re-frame` command with the application name and optional group/package ID:

```
$ lein new react-native-re-frame my-app
  OR
$ lein new react-native-re-frame com.mydomain/my-app
```

The project will be generated in the `my-app` folder in the current directory, with appropriate directory structures, bundle and package / application identifiers set for both iOS and Android. To begin developing, you should get your NPM dependencies installed. From the `my-app/` folder:

```
$ npm install
```

Next, have `shadow-cljs` begin watching your project's `src` directory:

```
$ shadow-cljs watch app
```

And finally, start React Native's JS packager so that your compiled ClojureScript can be reloaded onto your device:

```
$ ./node_modules/react-native/scripts/launchpackager.command
```

## Developing

### iOS

To start working with the iOS project, you'll need first to have [CocoaPods](https://cocoapods.org/) installed to manage the Xcode project's dependencies (including the React Native dependencies). From the `ios/` folder:

```
$ pod install
```

Once that's complete, simply build and run `MyApp.xcworkspace` as you would any iOS project.

### Android

For Android, you'll need to prepare the initial `index.android.bundle` for inclusion within the `assets` folder of the Android project. From the project root:

```
$ mkdir android/app/src/main/assets
$ npm run bundle-android-dev
```

Once that's complete, simply open the `android/` directory in Android Studio and build and run as you would any Android project.

You'll need to follow [React Native's instructions](https://facebook.github.io/react-native/docs/running-on-device.html) for connecting the running Android app to the development server so that your JS will make its way to the device.

#### Quick TL;DR:

Connect your Android device that has USB debugging enabled and run the following to find the device name: 
```
$ adb devices
```

Run the following to allow the Android device to disvocer the JS packager on your machine over USB:

```
$ adb -s <device-name> reverse tcp:8081 tcp:8081
```

Then enable "Live Reloading" from the developer menu. The developer menu can be accessed, while the app is running, by either shaking the device, or executing the following command:

```
$ adb shell input keyevent 82
```

## License

Copyright © 2019 7theta

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
