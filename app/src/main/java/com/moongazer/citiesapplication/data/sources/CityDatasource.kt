package com.moongazer.citiesapplication.data.sources

import com.moongazer.citiesapplication.arch.extensions.FlowResult
import com.moongazer.citiesapplication.data.models.City
import kotlinx.coroutines.flow.Flow

/**
 * @author ml-hungtruong
 */
interface CityDatasource {
    fun getCities(query: String): Flow<FlowResult<List<City>>>

    fun addCities(vararg cities: City)

    fun deleteCity(city: City)
}
