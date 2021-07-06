package com.moongazer.citiesapplication.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.bindCommonError
import androidx.lifecycle.bindLoading
import com.android.appname.arch.extensions.LoadingAware
import com.android.appname.arch.extensions.ViewErrorAware
import com.moongazer.citiesapplication.arch.extensions.FlowResult
import com.moongazer.citiesapplication.arch.extensions.onEachSuccess
import com.moongazer.citiesapplication.data.CityRepository
import com.moongazer.citiesapplication.data.models.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel(), LoadingAware, ViewErrorAware {

    private val cities = mutableListOf<City>()

    fun fetchCities(): Flow<FlowResult<List<City>>> {
        return cityRepository.getCities("a")
            .onEachSuccess {
                cities.clear()
                cities.addAll(it)
            }
            .bindLoading(this)
            .bindCommonError(this)
    }

    fun getCities(): List<City> = cities

    fun clearCities() {
        cities.clear()
    }
}
