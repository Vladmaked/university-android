package com.example.labfourth

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listItemDao(): ListItemDao
}