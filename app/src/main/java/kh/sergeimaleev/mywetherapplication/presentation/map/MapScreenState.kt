package kh.sergeimaleev.mywetherapplication.presentation.map

import com.google.android.gms.maps.model.LatLng

open class MapScreenState {
    object ShowDialogForecast : MapScreenState()
    data class MovedToLocation(val latLng: LatLng) : MapScreenState()
    object Loading : MapScreenState()
    object NoLoading : MapScreenState()
}
