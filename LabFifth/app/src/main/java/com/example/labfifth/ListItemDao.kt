package com.example.labfifth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListItemDao {
    @Query("SELECT * FROM ListItemEntity")
    fun getAll(): List<ListItemEntity>

    @Insert
    fun insert(item: ListItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg items: ListItemEntity)

    @Query("DELETE FROM ListItemEntity")
    suspend fun deleteAll()
}
