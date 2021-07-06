package com.moongazer.citiesapplication.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moongazer.citiesapplication.R
import com.moongazer.citiesapplication.data.models.City
import com.moongazer.citiesapplication.databinding.ItemCityBinding

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
            binding.tvLatLng.text =
                itemView.context.getString(R.string.city_lat_lng, city.lat, city.lng)
            Glide.with(binding.imgCity)
                .load("https://images.unsplash.com/photo-1480714378408-67cf0d13bc1b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2l0eXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80")
                .override(itemView.context.resources.getDimensionPixelSize(R.dimen.img_city_size))
                .centerCrop()
                .into(binding.imgCity)
        }
    }
}
