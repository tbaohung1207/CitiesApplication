package com.moongazer.citiesapplication.arch.modules

import android.content.Context
import androidx.room.Room
import com.moongazer.citiesapplication.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    companion object {
        private const val DATABASE_NAME = "CITY_APPLICATION_DB"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCityDao(appDatabase: AppDatabase) = appDatabase.cityDao()
}
