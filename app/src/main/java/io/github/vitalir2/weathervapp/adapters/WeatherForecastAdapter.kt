package io.github.vitalir2.weathervapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.databinding.ItemForecastBinding

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
        fun bind(daily: Daily) {
            binding.temperatureLabel.text = daily.temp.day.toString()
        }
    }
}