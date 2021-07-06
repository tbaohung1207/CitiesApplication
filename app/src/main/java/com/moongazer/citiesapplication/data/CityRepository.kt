package com.moongazer.citiesapplication.data

import com.moongazer.citiesapplication.arch.extensions.FlowResult
import com.moongazer.citiesapplication.data.local.CityLocalDatasource
import com.moongazer.citiesapplication.data.models.City
import com.moongazer.citiesapplication.data.network.CityRemoteDatasource
import com.moongazer.citiesapplication.data.sources.CityDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author ml-hungtruong
 */
class CityRepository @Inject constructor(
    private val cityLocalDatasource: CityLocalDatasource,
    private val cityRemoteDatasource: CityRemoteDatasource
) : CityDatasource {
    override fun getCities(query: String): Flow<FlowResult<List<City>>> = flow {
        cityLocalDatasource.getCities(query).collect {
            emit(it)
        }
        cityRemoteDatasource.getCities(query).collect {
            emit(it)
            (it as? FlowResult.Success)?.let { success ->
                addCities(*success.value.toTypedArray())
            }
        }
    }

    override fun addCities(vararg cities: City) {
        cityLocalDatasource.addCities(*cities)
    }

    override fun deleteCity(city: City) {
        cityLocalDatasource.deleteCity(city)
    }
}