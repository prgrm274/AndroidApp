package com.programmer270487.dansandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positionentity")
data class PositionEntity(
    val company: String,
    val company_logo: String,
    val company_url: String?,
    val created_at: String,
    val description: String,
    val how_to_apply: String,
    @PrimaryKey
    val id: String,
    val location: String,
    val title: String,
    val type: String,
    val url: String
)