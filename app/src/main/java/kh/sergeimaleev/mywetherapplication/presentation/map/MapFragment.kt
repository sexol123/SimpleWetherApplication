package kh.sergeimaleev.mywetherapplication.presentation.map

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.databinding.FragmentMapBinding
import kh.sergeimaleev.mywetherapplication.helpers.MapHelper
import kh.sergeimaleev.mywetherapplication.helpers.PermissionsManager
import kh.sergeimaleev.mywetherapplication.helpers.hideLoading
import kh.sergeimaleev.mywetherapplication.helpers.showLoading
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseFragment
import kh.sergeimaleev.mywetherapplication.presentation.base.TagNamed
import kh.sergeimaleev.mywetherapplication.presentation.map.dialog.AlertDialogWeatherForecast
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<MapViewModel, FragmentMapBinding>(), OnMapReadyCallback,
    TagNamed<Fragment> {
    override val mLayoutId: Int = R.layout.fragment_map
    override val mViewModel: MapViewModel by sharedViewModel()
    override val tagName: Int = R.string.map
    override val item: Fragment = this
    private val locationsViewModel: LocationsViewModel by viewModel()
    private lateinit var map: GoogleMap
    private lateinit var mapTypeDialog: MapTypeDialog
    private var myLocation: Location? = null
    private val alertWeatherForecast: AlertDialogWeatherForecast by lazy {
        AlertDialogWeatherForecast(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUiComponents(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        fragment_map_map_view?.onStart()
    }

    override fun onResume() {
        super.onResume()
        fragment_map_map_view?.onResume()
    }

    override fun onMapReady(m: GoogleMap) {
        MapsInitializer.initialize(this.requireContext())
        map = m
        PermissionsManager.runWithPermissions(
            requireActivity(),
            ::initMyLocation,
            ::showToastPermissions,
            ::showToastPermissions,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        map.setOnMyLocationChangeListener {
            myLocation = it
        }
        map.setOnMapClickListener {
            addMarkerToMap(it, true)
            getWeatherForecast(it.latitude, it.longitude)
        }
    }

    private fun getWeatherForecast(latitude: Double, longitude: Double) {
        showLoading()
        mViewModel.getWeatherForecast(LatLng(latitude, longitude))
    }

    private fun getWeatherForecastForCurrentLocation() {
        val mCurrentLocation: Location = locationsViewModel.getLastLocation()
            ?: myLocation
            ?: return showToast("Location is not reachable. Try late... ")

        getWeatherForecast(mCurrentLocation.latitude, mCurrentLocation.longitude)
    }

    private fun moveToPoint(latLng: LatLng) {
        map.clear()
        addMarkerToMap(latLng)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
    }

    private fun addMarkerToMap(latLng: LatLng, isDraggable: Boolean = false): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(latLng)
                .draggable(isDraggable)
                .title(latLng.toString())
        )
    }

    override fun onPause() {
        fragment_map_map_view?.onPause()
        super.onPause()
    }

    override fun onStop() {
        fragment_map_map_view?.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        fragment_map_map_view?.onDestroy()
        super.onDestroy()
    }

    private fun initUiComponents(savedInstanceState: Bundle?) {
        initScreenStateObserver()
        initMaps(savedInstanceState)
        initDialogs()
        initButtonsListeners()
        initDataObservers()
    }

    private fun initDialogs() {
        mapTypeDialog = MapTypeDialog(requireContext(), MapHelper.hashMap) {
            setMapType(it)
        }
    }

    private fun initScreenStateObserver() {
        mViewModel.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MapScreenState.MovedToLocation -> {
                    moveToPoint(it.latLng)
                }
            }
        })
    }

    private fun initDataObservers() {
        mViewModel.forecastWeather.observe(viewLifecycleOwner, Observer { weatherResponse ->
            hideLoading()
            alertWeatherForecast.show(weatherResponse)
            Log.d("Observer", "alert should be shown")
        })
    }

    private fun initMyLocation() {
        map.isMyLocationEnabled = true
    }

    private fun showToastPermissions() {
        Toast.makeText(this.context, "Need permissions", Toast.LENGTH_SHORT).show()
    }

    private fun showToast(s: String) {
        Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
    }

    private fun initMaps(savedInstanceState: Bundle?) {
        fragment_map_map_view.onCreate(savedInstanceState)
        fragment_map_map_view.getMapAsync(this)
    }

    private fun initButtonsListeners() {
        fragment_map_btn_map_type.setOnClickListener {
            mapTypeDialog.show()
        }

        fragment_map_btn_map_get_weather.setOnClickListener {
            getWeatherForecastForCurrentLocation()
        }
    }

    private fun setMapType(mapType: Int) {
        map.mapType = if (mapType in MapHelper.hashMap.keys.indices) mapType else return
    }
}

