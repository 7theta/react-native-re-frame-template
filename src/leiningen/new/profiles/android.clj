(ns leiningen.new.profiles.android)

(defn files [data options render]
  (let [{:keys [android-package]} data
        android-java-path (clojure.string/replace android-package #"\." "/")]
    
    [["android/build.gradle" (render "android/build.gradle" data)]
     ["android/gradle.properties" (render "android/gradle.properties" data)]
     ["android/gradlew" (render "android/gradlew")]
     ["android/gradlew.bat" (render "android/gradlew.bat")]
     ["android/settings.gradle" (render "android/settings.gradle" data)]
     
     ["android/gradle/wrapper/gradle-wrapper.jar" (render "android/gradle/wrapper/gradle-wrapper.jar")]
     ["android/gradle/wrapper/gradle-wrapper.properties" (render "android/gradle/wrapper/gradle-wrapper.properties" data)]
     
     ["android/app/build.gradle" (render "android/app/build.gradle" data)]
     ["android/app/proguard-rules.pro" (render "android/app/proguard-rules.pro" data)]
     
     ["android/app/src/main/AndroidManifest.xml" (render "android/app/src/main/AndroidManifest.xml" data)]
     #_["android/app/src/main/assets" (render "android/app/src/main/assets")]
     
     [(str "android/app/src/main/java/" android-java-path "/MainActivity.kt") (render (str "android/app/src/main/java/" "template" "/MainActivity.kt") data)]
     [(str "android/app/src/main/java/" android-java-path "/MainApplication.kt") (render (str "android/app/src/main/java/" "template" "/MainApplication.kt") data)]
     [(str "android/app/src/main/java/" android-java-path "/ReactFragment.kt") (render (str "android/app/src/main/java/" "template" "/ReactFragment.kt") data)]
     
     [(str "android/app/src/androidTest/java/" android-java-path "/ExampleInstrumentedTest.kt") (render (str "android/app/src/androidTest/java/" "template" "/ExampleInstrumentedTest.kt") data)]
     [(str "android/app/src/test/java/" android-java-path "/ExampleUnitTest.kt") (render (str "android/app/src/test/java/" "template" "/ExampleUnitTest.kt") data)]
     
     ["android/app/src/main/res/drawable/ic_launcher_background.xml" (render "android/app/src/main/res/drawable/ic_launcher_background.xml" data)]
     ["android/app/src/main/res/drawable-v24/ic_launcher_foreground.xml" (render "android/app/src/main/res/drawable-v24/ic_launcher_foreground.xml" data)]
     
     ["android/app/src/main/res/layout/activity_main.xml" (render "android/app/src/main/res/layout/activity_main.xml" data)]
     
     ["android/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml" (render "android/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml" data)]
     ["android/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml" (render "android/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml" data)]
     ["android/app/src/main/res/mipmap-hdpi/ic_launcher_round.png" (render "android/app/src/main/res/mipmap-hdpi/ic_launcher_round.png")]
     ["android/app/src/main/res/mipmap-hdpi/ic_launcher.png" (render "android/app/src/main/res/mipmap-hdpi/ic_launcher.png")]
     ["android/app/src/main/res/mipmap-mdpi/ic_launcher_round.png" (render "android/app/src/main/res/mipmap-mdpi/ic_launcher_round.png")]
     ["android/app/src/main/res/mipmap-mdpi/ic_launcher.png" (render "android/app/src/main/res/mipmap-mdpi/ic_launcher.png")]
     ["android/app/src/main/res/mipmap-xhdpi/ic_launcher_round.png" (render "android/app/src/main/res/mipmap-xhdpi/ic_launcher_round.png")]
     ["android/app/src/main/res/mipmap-xhdpi/ic_launcher.png" (render "android/app/src/main/res/mipmap-xhdpi/ic_launcher.png")]
     ["android/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png" (render "android/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png")]
     ["android/app/src/main/res/mipmap-xxhdpi/ic_launcher.png" (render "android/app/src/main/res/mipmap-xxhdpi/ic_launcher.png")]
     ["android/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png" (render "android/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png")]
     ["android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" (render "android/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png")]
     
     ["android/app/src/main/res/values/colors.xml" (render "android/app/src/main/res/values/colors.xml" data)]
     ["android/app/src/main/res/values/strings.xml" (render "android/app/src/main/res/values/strings.xml" data)]
     ["android/app/src/main/res/values/styles.xml" (render "android/app/src/main/res/values/styles.xml" data)]
     
     ["android/app/src/main/res/xml/network_security_config.xml" (render "android/app/src/main/res/xml/network_security_config.xml" data)]]))
