package com.mevalera.pruebayape.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mevalera.pruebayape.data.models.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class TestAppDatabase : RoomDatabase() {
    abstract fun testAppDao(): TestAppDao
}