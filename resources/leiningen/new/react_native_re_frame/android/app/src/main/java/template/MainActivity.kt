package {{android-package}}

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity
import com.facebook.react.modules.core.PermissionListener

class MainActivity : FragmentActivity(), ReactFragment.ActivityDelegate {

    companion object {

        const val reactFragmentTag = "React"
    }

    lateinit var reactFragment: ReactFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ReactFragment.newInstance("{{ns-name}}")
        reactFragment = fragment

        supportFragmentManager
            .beginTransaction()
            .add(R.id.react_fragment_container, fragment, reactFragmentTag)
            .commit()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        reactFragment.onNewIntent(intent)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onBackPressed() {
        if (!reactFragment.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return reactFragment.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return reactFragment.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
        return reactFragment.onKeyLongPress(keyCode, event) || super.onKeyLongPress(keyCode, event)
    }

    override fun requestPermissions(permissions: Array<out String>, requestCode: Int, listener: PermissionListener) {
        reactFragment.requestPermissions(permissions, requestCode, listener)
    }
}
