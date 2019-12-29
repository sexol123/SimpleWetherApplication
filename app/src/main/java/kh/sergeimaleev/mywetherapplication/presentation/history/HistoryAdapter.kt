package kh.sergeimaleev.mywetherapplication.presentation.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import kh.sergeimaleev.mywetherapplication.R
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.helpers.RECEIVED_TIME_FORMAT_PATTERN
import kh.sergeimaleev.mywetherapplication.helpers.fromCharSequence
import kh.sergeimaleev.mywetherapplication.helpers.parseToString
import kotlinx.android.synthetic.main.history_item.view.*
import java.util.*

class HistoryAdapter(
    private val listHistory: ArrayList<WeatherForecastEntity> = arrayListOf(),
    private val onItemClickAction: ((latitude: Double, longitude: Double) -> Unit)? = null
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val layoutId = R.layout.history_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int = listHistory.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(listHistory[position], View.OnClickListener {
            onItemClickAction?.invoke(listHistory[position].lat, listHistory[position].lon)
        })
    }

    fun update(newList: List<WeatherForecastEntity>) {
        listHistory.clear()
        listHistory.addAll(newList)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(
        private val viewItem: View
    ) : RecyclerView.ViewHolder(viewItem) {

        private var location: LatLng
            get() {
                val b = viewItem.history_item_location.text.split(
                    delimiters = *charArrayOf(','),
                    ignoreCase = true,
                    limit = 2
                )
                return LatLng(b[0].toDouble(), b[1].toDouble())
            }
            private set(value) {
                viewItem.history_item_location.text =
                    "lat: " + value.latitude.toString() + " lon: " + value.longitude.toString()
            }

        private var time: Date
            get() = viewItem.history_item_time.text.fromCharSequence()
            private set(value) {
                viewItem.history_item_time.text = value.parseToString(RECEIVED_TIME_FORMAT_PATTERN)
            }

        private var localTime: Date
            get() = viewItem.tv_local_time_value.text.fromCharSequence()
            private set(value) {
                viewItem.tv_local_time_value.text =
                    value.parseToString(RECEIVED_TIME_FORMAT_PATTERN)
            }


        private var onClickListener: View.OnClickListener? = null
            private set(value) {
                itemView.setOnClickListener(value)
            }

        internal fun bind(
            historyItem: WeatherForecastEntity,
            onClickListener: View.OnClickListener
        ) {
            with(itemView) {
                tv_number_id_value.text = historyItem.id.toString()
                location = LatLng(historyItem.lat, historyItem.lon)
                time = historyItem.dt.fromCharSequence()
                localTime = Date(historyItem.dtLocal)
                tv_pressure.text = historyItem.pressure.toString()
                tv_description.text = historyItem.weatherDescription
                tv_wind.text = historyItem.windSpeed
                tv_name.text = historyItem.name
                tv_temp_min.text = historyItem.tempMin.toString()
                tv_temp_max.text = historyItem.tempMax.toString()
            }
            this.onClickListener = onClickListener
        }
    }
}