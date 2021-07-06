package com.moongazer.citiesapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moongazer.citiesapplication.data.entities.CityEntity
import com.moongazer.citiesapplication.data.local.database.daos.CityDao

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
