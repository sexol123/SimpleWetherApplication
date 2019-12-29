package kh.sergeimaleev.mywetherapplication.presentation.map.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.helpers.RECEIVED_TIME_FORMAT_PATTERN
import kh.sergeimaleev.mywetherapplication.helpers.fromCharSequence
import kh.sergeimaleev.mywetherapplication.helpers.invisible
import kh.sergeimaleev.mywetherapplication.helpers.parseToString
import kotlinx.android.synthetic.main.history_item.*
import java.util.*

class AlertDialogWeatherForecast(
    context: Context
) {
    private val mLayout: Int = R.layout.history_item
    private val alert = AlertDialog.Builder(context)
        .setView(mLayout)
        .setCancelable(true)
        .setIcon(R.drawable.ic_umbrella)
        .create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


    fun show(weatherForecastEntity: WeatherForecastEntity) {
        if (!alert.isShowing) {
            alert.setOnShowListener {
                setData(weatherForecastEntity)
            }
            alert.show()
        }
    }

    @SuppressLint("Public method")
    fun hide() {
        if (alert.isShowing) {
            alert.dismiss()
        }
    }

    private var time: Date
        get() = alert.history_item_time.text.fromCharSequence()
        private set(value) {
            alert.history_item_time.text = value.parseToString(RECEIVED_TIME_FORMAT_PATTERN)
        }

    private var localTime: Date
        get() = alert.tv_local_time_value.text.fromCharSequence()
        private set(value) {
            alert.tv_local_time_value.text =
                value.parseToString(RECEIVED_TIME_FORMAT_PATTERN)
        }

    private fun setData(weatherForecastEntity: WeatherForecastEntity) {
        if (!alert.isShowing) return

        alert.tv_number_id_value.invisible()

        alert.tv_number_id_value.text = weatherForecastEntity.id.toString()
        alert.history_item_location.text =
            "lat: ${weatherForecastEntity.lat} lon: ${weatherForecastEntity.lon}"
        time = weatherForecastEntity.dt.fromCharSequence()
        localTime = Date(weatherForecastEntity.dtLocal)
        alert.tv_pressure.text = weatherForecastEntity.pressure.toString()
        alert.tv_description.text = weatherForecastEntity.weatherDescription
        alert.tv_wind.text = weatherForecastEntity.windSpeed
        alert.tv_name.text = weatherForecastEntity.name
        alert.tv_temp_min.text = weatherForecastEntity.tempMin.toString()
        alert.tv_temp_max.text = weatherForecastEntity.tempMax.toString()
    }
}