package eco.com.gurunanak

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import java.util.*

class Classt : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main);
    }

    fun requestPhone(view: View) {
        Permissions.check(this, Manifest.permission.CALL_PHONE, null, object : PermissionHandler() {
            override fun onGranted() {
                Toast.makeText(this@Classt, "Phone granted.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun requestCameraAndStorage(view: View) {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        Permissions.check(this, permissions, null, null, object : PermissionHandler() {
            override fun onGranted() {
                Toast.makeText(this@Classt, "Camera+Storage granted.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun requestLocation(view: View) {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        val rationale = "Please provide location permission so that you can ..."
        val options = Permissions.Options()
                .setRationaleDialogTitle("Info")
                .setSettingsDialogTitle("Warning")

        Permissions.check(this, permissions, rationale, options, object : PermissionHandler() {
            override fun onGranted() {
                Toast.makeText(this@Classt, "Location granted.", Toast.LENGTH_SHORT).show()
            }

            override fun onDenied(context: Context, deniedPermissions: ArrayList<String>) {
                Toast.makeText(this@Classt, "Location denied.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun openSettings(view: View) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
        startActivity(intent)
    }

}