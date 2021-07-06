package com.moongazer.citiesapplication.data.models

import com.moongazer.citiesapplication.data.entities.CityEntity

/**
 * @author ml-hungtruong
 */
data class City(
    val id: Long,
    val title: String,
    val locationType: String,
    val lat: Double,
    val lng: Double
) {
    fun toEntity() = CityEntity(id, title, locationType, lat, lng)
}
