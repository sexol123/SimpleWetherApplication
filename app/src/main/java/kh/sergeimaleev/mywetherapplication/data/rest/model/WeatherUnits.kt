package kh.sergeimaleev.mywetherapplication.data.rest.model

enum class WeatherUnits(unit: String) {
    STANDART(""),
    METRIC("metric"),
    IMPERIAL("imperial");

    fun getValue(v: WeatherUnits) = v.name
}