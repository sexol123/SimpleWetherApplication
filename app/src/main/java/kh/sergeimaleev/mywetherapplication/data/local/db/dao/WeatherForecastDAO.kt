package kh.sergeimaleev.mywetherapplication.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity.Companion.TABLE_NAME

@Dao
interface WeatherForecastDAO {

    @Query("SELECT * FROM $TABLE_NAME ORDER by id DESC LIMIT 1")
    fun getAllWeatherForecastLast(): LiveData<WeatherForecastEntity>

    @Query("SELECT * FROM $TABLE_NAME ORDER by id DESC")
    fun getAllWeatherForecast(): LiveData<List<WeatherForecastEntity>>

    @Query("SELECT * FROM $TABLE_NAME ORDER by id DESC")
    suspend fun getAllWeatherForecastList(): List<WeatherForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(forecast: WeatherForecastEntity)

    @Delete
    suspend fun deleteWeatherForecast(forecast: WeatherForecastEntity)

}