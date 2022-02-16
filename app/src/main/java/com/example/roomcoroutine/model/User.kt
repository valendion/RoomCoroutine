package com.example.roomcoroutine.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var nis: String,
    @ColumnInfo var name: String? = null,
    @ColumnInfo var phone: String? = null
)
