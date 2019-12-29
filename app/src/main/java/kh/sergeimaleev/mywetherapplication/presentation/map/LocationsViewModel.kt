package kh.sergeimaleev.mywetherapplication.presentation.map

import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class LocationsViewModel(app: Application) : AndroidViewModel(app) {
    companion object {
        private const val TAG = "Locations"
        private const val LOCATION_INTERVAL = 1000L
        private const val LOCATION_DISTANCE = 10f
    }

    private val locationListenerNetwork = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d(TAG, location.toString())
            lastLocationNetwork = location
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            Log.d(TAG, "Status changed: $provider - $status")
        }

        override fun onProviderEnabled(provider: String?) {
            Log.d(TAG, "Provider Enabled: $provider")
        }

        override fun onProviderDisabled(provider: String?) {
            Log.d(TAG, "Provider Disabled: $provider")
        }
    }
    private val locationListenerGPS = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d(TAG, location.toString())
            lastLocationGPS = location
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            Log.d(TAG, "Status changed: $provider - $status")
        }

        override fun onProviderEnabled(provider: String?) {
            Log.d(TAG, "Provider Enabled: $provider")
        }

        override fun onProviderDisabled(provider: String?) {
            Log.d(TAG, "Provider Disabled: $provider")
        }
    }
    private val locationManager: LocationManager =
        app.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var lastLocationGPS: Location? = null
    private var lastLocationNetwork: Location? = null

    init {
        initLocationsUpdate()
    }

    fun getLastLocation() = lastLocationGPS ?: lastLocationNetwork

    private fun initLocationsUpdate() {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                locationListenerNetwork
            )
        } catch (ex: SecurityException) {
            Log.i("Location", "fail to request location update, ignore $ex")
        } catch (ex: IllegalArgumentException) {
            Log.i("Location", "network provider does not exist, " + ex.message)
        }
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                locationListenerGPS
            )
        } catch (ex: SecurityException) {
            Log.i("Location", "fail to request location update, ignore $ex")
        } catch (ex: IllegalArgumentException) {
            Log.i("Location", "network provider does not exist, " + ex.message)
        }
    }
}