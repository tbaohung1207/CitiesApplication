package com.moongazer.citiesapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moongazer.citiesapplication.data.models.City

@Entity
data class CityEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val locationType: String,
    val lat: Double,
    val lng: Double
) {
    fun toModel() = City(id, title, locationType, lat, lng)
}
