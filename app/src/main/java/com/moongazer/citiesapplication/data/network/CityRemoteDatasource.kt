package com.moongazer.citiesapplication.data.network

import com.moongazer.citiesapplication.arch.extensions.FlowResult
import com.moongazer.citiesapplication.arch.extensions.mapIfSuccess
import com.moongazer.citiesapplication.arch.extensions.safeApiCall
import com.moongazer.citiesapplication.data.models.City
import com.moongazer.citiesapplication.data.sources.CityDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author ml-hungtruong
 */
class CityRemoteDatasource @Inject constructor(private val api: Api) : CityDatasource {
    override fun getCities(query: String): Flow<FlowResult<List<City>>> =
        safeApiCall { api.findLocation(query) }.mapIfSuccess {
            it.map { response ->
                val latLng = response.latLng.split(",")
                City(
                    response.woeid,
                    response.title,
                    response.locationType,
                    latLng.getOrNull(0)?.trim()?.toDoubleOrNull() ?: 0.0,
                    latLng.getOrNull(1)?.trim()?.toDoubleOrNull() ?: 0.0
                )
            }
        }

    override fun addCities(vararg cities: City) {
        // Ignore
    }

    override fun deleteCity(city: City) {
        // Ignore
    }
}
