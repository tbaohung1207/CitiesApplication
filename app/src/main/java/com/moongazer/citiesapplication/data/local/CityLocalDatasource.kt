package com.moongazer.citiesapplication.data.local

import com.moongazer.citiesapplication.arch.extensions.FlowResult
import com.moongazer.citiesapplication.arch.extensions.safeFlow
import com.moongazer.citiesapplication.data.entities.CityEntity
import com.moongazer.citiesapplication.data.local.database.daos.CityDao
import com.moongazer.citiesapplication.data.models.City
import com.moongazer.citiesapplication.data.sources.CityDatasource
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author ml-hungtruong
 */
class CityLocalDatasource @Inject constructor(private val cityDao: CityDao) : CityDatasource {

    override fun getCities(query: String) = safeFlow { cityDao.findAllCities().map(CityEntity::toModel) }

    override suspend fun addCities(vararg cities: City) {
        cityDao.insertAll(cities.map(City::toEntity))
    }

    override suspend fun deleteCity(city: City) {
        cityDao.delete(city.toEntity())
    }
}
