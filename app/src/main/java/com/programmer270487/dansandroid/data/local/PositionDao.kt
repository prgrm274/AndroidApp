package com.programmer270487.dansandroid.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.programmer270487.dansandroid.data.local.PositionEntity

@Dao
interface PositionDao {
    @Upsert
    suspend fun upsertAll(entities: List<PositionEntity>)

    @Query("SELECT * FROM positionentity")
    fun pagingSource(): PagingSource<Int, PositionEntity>

    @Query("DELETE FROM positionentity")
    suspend fun clearAll()
}