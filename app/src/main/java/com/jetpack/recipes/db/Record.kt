package com.jetpack.recipes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "duration") val duration: Int,
)
