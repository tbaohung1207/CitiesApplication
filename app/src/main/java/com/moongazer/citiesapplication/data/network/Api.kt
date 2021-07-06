package com.moongazer.citiesapplication.data.network

import com.moongazer.citiesapplication.data.network.responses.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/location/search")
    fun findLocation(@Query("query") query: String): Response<List<LocationResponse>>
}
