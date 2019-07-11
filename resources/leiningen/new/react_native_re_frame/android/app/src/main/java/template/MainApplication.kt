package {{android-package}}

import android.app.Application
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import java.util.*

class MainApplication : Application(), ReactApplication {

    private val host = object : ReactNativeHost(this) {

        override fun getUseDeveloperSupport() = BuildConfig.DEBUG

        override fun getPackages(): List<ReactPackage> = Arrays.asList(
            MainReactPackage()
        )
    }

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
    }

    override fun getReactNativeHost() = host
}
