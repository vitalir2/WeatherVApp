package io.github.vitalir2.weathervapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.vitalir2.weathervapp.R
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.databinding.ItemForecastBinding
import io.github.vitalir2.weathervapp.utils.fromStringDayOfWeekToInt
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*

class WeatherForecastDiffCallback : DiffUtil.ItemCallback<Daily>() {
    override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
        return oldItem == newItem
    }

}

class WeatherForecastAdapter : ListAdapter<Daily, WeatherForecastAdapter.ViewHolder>(WeatherForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
        parent,
        false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {

        private val daysOfWeek = binding.root.resources.getStringArray(R.array.DayOfWeek)

        fun bind(daily: Daily) {
            val time = Instant.fromEpochSeconds(daily.dt)
            val localDate = time.toLocalDateTime(TimeZone.currentSystemDefault())
            binding.dayWeek.text =
                daysOfWeek[fromStringDayOfWeekToInt(localDate.dayOfWeek.toString())]
            val localDateText = "${localDate.dayOfMonth}"
            binding.dateLabel.text = localDateText
            val temperatureMaxString = daily.temp.max.toString() + "\u00B0"
            val temperatureMinString = daily.temp.min.toString() + "\u00B0"
            binding.temperatureMax.text = temperatureMaxString
            binding.temperatureMin.text = temperatureMinString
            val url = "https://openweathermap.org/img/wn/${daily.weather[0].icon}@2x.png"
            Glide
                .with(binding.root.context)
                .load(url)
                .into(binding.weatherImage)
        }
    }
}