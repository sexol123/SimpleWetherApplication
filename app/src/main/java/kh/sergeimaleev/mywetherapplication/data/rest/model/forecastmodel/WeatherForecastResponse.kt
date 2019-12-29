package kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel


import com.google.gson.annotations.SerializedName
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity

data class WeatherForecastResponse(
    val city: City = City(),
    val cnt: Int = 0,
    val cod: String = "",
    val list: List<X> = listOf(),
    val message: Double = 0.0
) {
    data class City(
        val coord: Coord = Coord(),
        val country: String = "",
        val id: Int = 0,
        val name: String = "",
        val population: Int = 0,
        val sunrise: Int = 0,
        val sunset: Int = 0,
        val timezone: Int = 0
    ) {
        data class Coord(
            val lat: Double = 0.0,
            val lon: Double = 0.0
        )
    }

    data class X(
        val clouds: Clouds = Clouds(),
        val dt: Int = 0,
        @SerializedName("dt_txt")
        val dtTxt: String = "",
        val main: Main = Main(),
        val rain: Rain = Rain(),
        val sys: Sys = Sys(),
        val weather: List<Weather> = listOf(),
        val wind: Wind = Wind()
    ) {
        data class Clouds(
            val all: Int = 0
        )

        data class Main(
            @SerializedName("feels_like")
            val feelsLike: Double = 0.0,
            @SerializedName("grnd_level")
            val grndLevel: Int = 0,
            val humidity: Int = 0,
            val pressure: Int = 0,
            @SerializedName("sea_level")
            val seaLevel: Int = 0,
            val temp: Double = 0.0,
            @SerializedName("temp_kf")
            val tempKf: Double = 0.0,
            @SerializedName("temp_max")
            val tempMax: Double = 0.0,
            @SerializedName("temp_min")
            val tempMin: Double = 0.0
        )

        data class Rain(
            @SerializedName("3h")
            val h: Double = 0.0
        )

        data class Sys(
            val pod: String = ""
        )

        data class Weather(
            val description: String = "",
            val icon: String = "",
            val id: Int = 0,
            val main: String = ""
        )

        data class Wind(
            val deg: Int = 0,
            val speed: Double = 0.0
        )
    }

    fun mapToWeatherForecastEntity(): WeatherForecastEntity {
        return WeatherForecastEntity(
            id = 0,
            dt = this.list[0].dtTxt,
            dtLocal = System.currentTimeMillis(),
            windSpeed = this.list[0].wind.speed.toString(),
            weatherDescription = this.list[0].weather[0].description,
            pressure = this.list[0].main.pressure,
            message = this.message,
            name = this.city.name,
            lat = this.city.coord.lat,
            lon = this.city.coord.lon,
            tempMax = this.list[0].main.tempMax,
            tempMin = this.list[0].main.tempMin
        )
    }
}