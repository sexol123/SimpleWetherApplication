package kh.sergeimaleev.mywetherapplication.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kh.sergeimaleev.mywetherapplication.helpers.PermissionsManager

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val mLayout: Int
    protected abstract val mHostId: Int
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayout)
        setupNavController()
        onCreate()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PermissionsManager.PERMISSIONS_REQUEST_CODE) {
            PermissionsManager.checkAllPermissions(permissions, grantResults)
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    protected abstract fun onCreate()

    private fun setupNavController() {
        navController = Navigation.findNavController(this, mHostId)
    }
}