package kh.sergeimaleev.mywetherapplication.data.rest.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class WeatherCurrentResponse(
    val base: String = "", // stations
    val clouds: Clouds = Clouds(),
    val cod: Int = 0, // 200
    val coord: Coord = Coord(),
    val dt: Long = 0, // 1485789600
    val id: Int = 0, // 2643743
    val main: Main = Main(),
    val name: String = "", // London
    val sys: Sys = Sys(),
    val visibility: Int = 0, // 10000
    val weather: List<Weather> = listOf(),
    val wind: Wind = Wind()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Clouds(
        val all: Int = 0 // 90
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Coord(
        val lat: Double = 0.0, // 51.51
        val lon: Double = 0.0 // -0.13
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Main(
        val humidity: Int = 0, // 81
        val pressure: Int = 0, // 1012
        val temp: Double = 0.0, // 280.32
        @SerializedName("temp_max")
        val tempMax: Double = 0.0, // 281.15
        @SerializedName("temp_min")
        val tempMin: Double = 0.0 // 279.15
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Sys(
        val country: String = "", // GB
        val id: Int = 0, // 5091
        val message: Double = 0.0, // 0.0103
        val sunrise: Int = 0, // 1485762037
        val sunset: Int = 0, // 1485794875
        val type: Int = 0 // 1
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Weather(
        val description: String = "", // light intensity drizzle
        val icon: String = "", // 09d
        val id: Int = 0, // 300
        val main: String = "" // Drizzle
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Wind(
        val deg: Int = 0, // 80
        val speed: Double = 0.0 // 4.1
    ) : Parcelable
}