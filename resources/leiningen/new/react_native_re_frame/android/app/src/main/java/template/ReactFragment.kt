package {{android-package}}

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.facebook.react.ReactApplication
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.facebook.react.devsupport.DoubleTapReloadRecognizer
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.modules.core.PermissionAwareActivity
import com.facebook.react.modules.core.PermissionListener

class ReactFragment : Fragment() {

    interface ActivityDelegate : DefaultHardwareBackBtnHandler, PermissionAwareActivity, ActivityForwarding

    // Activity should implement and forward these methods to the ReactFragment
    interface ActivityForwarding {

        fun onNewIntent(intent: Intent)
        fun onBackPressed()
        fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean
        fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean
        fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean
    }

    var reactView: ReactRootView? = null
        private set

    val host: ReactNativeHost
        get() = (activity!!.application as ReactApplication).reactNativeHost

    val delegate: ActivityDelegate
        get() = activity as ActivityDelegate

    private var permissionListener: PermissionListener? = null
    private var doubleTapRecognizer: DoubleTapReloadRecognizer? = null
    private var executeOnResume: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doubleTapRecognizer = DoubleTapReloadRecognizer()
    }

    fun onNewIntent(intent: Intent) {
        host.withInstance { instanceManager ->
            instanceManager.onNewIntent(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args = arguments!!
        val mainComponentName = args.getString(argComponentName)!!
        val launchOptions = args.getBundle(argLaunchOptions)

        val reactView = ReactRootView(activity!!)
        reactView.startReactApplication(
            host.reactInstanceManager,
            mainComponentName,
            launchOptions
        )
        this.reactView = reactView

        return reactView
    }

    override fun onResume() {
        super.onResume()
        host.withInstance { instanceManager ->
            instanceManager.onHostResume(activity!!, delegate)
        }
        if (executeOnResume != null) {
            executeOnResume?.invoke()
            executeOnResume = null
        }
    }

    override fun onPause() {
        host.withInstance { instanceManager ->
            instanceManager.onHostPause(activity!!)
        }
        super.onPause()
    }

    override fun onDestroyView() {
        reactView!!.unmountReactApplication()
        reactView = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        doubleTapRecognizer = null
        host.withInstance { instanceManager ->
            instanceManager.onHostDestroy(activity!!)
        }
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        host.withInstance { instanceManager ->
            instanceManager.onActivityResult(activity!!, requestCode, resultCode, data)
        }
    }

    fun requestPermissions(permissions: Array<out String>, requestCode: Int, listener: PermissionListener) {
        permissionListener = listener
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        executeOnResume = {
            this.permissionListener?.onRequestPermissionsResult(requestCode, permissions, grantResults)
            this.permissionListener = null
        }
    }

    fun onBackPressed(): Boolean {
        if (!host.hasInstance()) {
            return false
        }
        host.reactInstanceManager.onBackPressed()
        return true
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (host.hasInstance() && host.useDeveloperSupport && keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD) {
            event.startTracking()
            return true
        }
        return false
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (host.hasInstance() && host.useDeveloperSupport) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                host.reactInstanceManager.showDevOptionsDialog()
                return true
            }
            val didDoubleTapR = doubleTapRecognizer!!.didDoubleTapR(keyCode, activity!!.currentFocus)
            if (didDoubleTapR) {
                host.reactInstanceManager.devSupportManager.handleReloadJS()
                return true
            }
        }
        return false
    }

    fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
        if (host.hasInstance() && host.useDeveloperSupport && keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD) {
            host.reactInstanceManager.showDevOptionsDialog()
            return true
        }
        return false
    }

    companion object {

        private const val intentPrefix = "com.facebook.react.ReactFragment"
        private const val argComponentName = "$intentPrefix.intent.arg.COMPONENT_NAME"
        private const val argLaunchOptions = "$intentPrefix.intent.arg.LAUNCH_OPTIONS"

        fun newInstance(componentName: String, launchOptions: Bundle? = null): ReactFragment {
            val fragment = ReactFragment()
            val args = Bundle()
            args.putString(argComponentName, componentName)
            args.putBundle(argLaunchOptions, launchOptions)
            fragment.arguments = args
            return fragment
        }
    }
}

fun ReactNativeHost.withInstance(fn: (ReactInstanceManager) -> Unit) {
    if (hasInstance()) {
        fn(reactInstanceManager)
    }
}
