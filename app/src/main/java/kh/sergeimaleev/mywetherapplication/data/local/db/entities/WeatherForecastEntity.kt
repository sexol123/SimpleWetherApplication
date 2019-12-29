package kh.sergeimaleev.mywetherapplication.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WeatherForecastEntity.TABLE_NAME)
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dt: String = "",
    val pressure: Int = 0,
    val weatherDescription: String = "",
    val windSpeed: String = "",
    val message: Double = 0.0,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val name: String = "",
    val tempMax: Double = 0.0,
    val tempMin: Double = 0.0,
    val dtLocal: Long
) {
    companion object {
        const val TABLE_NAME = "weather_forecast"
    }
}