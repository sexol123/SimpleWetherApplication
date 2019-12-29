package kh.sergeimaleev.mywetherapplication.helpers

import com.google.android.gms.maps.GoogleMap
import kh.sergeimaleev.mywetherapplication.R

object MapHelper {
    const val NORMAL = R.string.map_type_normal
    const val HYBRID = R.string.map_type_hybrid
    const val SATELLITE = R.string.map_type_satellite
    const val TERRAIN = R.string.map_type_terrain

    val hashMap: HashMap<Int, Int> = hashMapOf(
        Pair(GoogleMap.MAP_TYPE_NORMAL, NORMAL),
        Pair(GoogleMap.MAP_TYPE_HYBRID, HYBRID),
        Pair(GoogleMap.MAP_TYPE_TERRAIN, TERRAIN),
        Pair(GoogleMap.MAP_TYPE_SATELLITE, SATELLITE)
    )

    fun getValue(key: Int) = hashMap[key]
}