package com.moongazer.citiesapplication.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author ml-hungtruong
 */
@Serializable
data class LocationResponse(
    @SerialName("title") val title: String,
    @SerialName("location_type") val locationType: String,
    @SerialName("woeid") val woeid: Long,
    @SerialName("latt_long") val latLng: String
)
