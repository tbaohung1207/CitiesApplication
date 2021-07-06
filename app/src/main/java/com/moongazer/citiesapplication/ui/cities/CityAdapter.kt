package com.moongazer.citiesapplication.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moongazer.citiesapplication.R
import com.moongazer.citiesapplication.data.models.City
import com.moongazer.citiesapplication.databinding.ItemCityBinding

/**
 * @author ml-hungtruong
 */
class CityAdapter(private val cities: List<City>) :
    RecyclerView.Adapter<CityAdapter.ItemCityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemCityViewHolder(
        ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemCityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount() = cities.size

    /**
     * Holder for item city
     */
    class ItemCityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.tvTitle.text = city.title
            binding.tvLatLng.text = itemView.context.getString(R.string.city_lat_lng, city.lat, city.lng)
        }
    }
}
