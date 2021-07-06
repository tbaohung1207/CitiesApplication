package com.moongazer.citiesapplication.data.local.database.daos

import androidx.room.*
import com.moongazer.citiesapplication.data.entities.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cityentity")
    suspend fun findAllCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<CityEntity>)

    @Delete
    suspend fun delete(entity: CityEntity)
}
