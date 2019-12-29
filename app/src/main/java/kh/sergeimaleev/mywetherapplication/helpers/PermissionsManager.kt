package kh.sergeimaleev.mywetherapplication.helpers

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionsManager {
    const val PERMISSIONS_REQUEST_CODE = 1_000
    private var onNotGrantedAction: (() -> Unit)? = null
    private var onRationaleAction: (() -> Unit)? = null
    lateinit var onGrantedAction: () -> Unit private set
    private lateinit var permissionList: List<String>

    fun checkAllPermissions(permissions: Array<out String>, grantResults: IntArray) {
        for (i in permissions.indices) {
            val res = check(permissions[i], grantResults[i])
            if (res) {
                onGrantedAction()
            } else {
                onNotGrantedAction?.invoke()
                break
            }
        }
    }

    fun runWithPermissions(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (() -> Unit)? = null,
        onRationale: (() -> Unit)? = null,
        vararg permissions: String
    ) {
        permissionList = permissions.toList()
        onNotGrantedAction = onFailure
        onRationaleAction = onRationale
        onGrantedAction = onSuccess

        if (ContextCompat.checkSelfPermission(
                activity,
                permissions.toString()
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permissions.toString()
                )
            ) {
                onRationaleAction?.invoke()
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    permissions,
                    PERMISSIONS_REQUEST_CODE
                )
            }
        } else {
            onSuccess.invoke()
        }
    }

    private fun check(permission: String, grantResult: Int): Boolean {
        return (permissionList.contains(permission) &&
                grantResult == PackageManager.PERMISSION_GRANTED)
    }
}