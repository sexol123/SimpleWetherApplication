package kh.sergeimaleev.mywetherapplication.presentation.history

import com.google.android.gms.maps.model.LatLng

data class HistoryModel(
    val number: Int = 0,
    val location: LatLng = LatLng(0.0, 0.0),
    val time: String = String()
)