package com.example.labfourth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val title: String,
    val description: String? = null
)
