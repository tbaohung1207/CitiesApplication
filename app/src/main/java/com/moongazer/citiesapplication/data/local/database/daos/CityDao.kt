package com.moongazer.citiesapplication.data.local.database.daos

import androidx.room.*
import com.moongazer.citiesapplication.data.entities.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cityentity")
    fun findAllCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: List<CityEntity>)

    @Delete
    fun delete(entity: CityEntity)
}
