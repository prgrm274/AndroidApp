package com.programmer270487.dansandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PositionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class PositionDb: RoomDatabase() {
    abstract val dao: PositionDao
}