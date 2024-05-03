package com.programmer270487.dansandroid.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.programmer270487.dansandroid.data.local.PositionDb
import com.programmer270487.dansandroid.data.local.PositionEntity
import com.programmer270487.dansandroid.data.mappers.toPositionEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PositionRemoteMediator(
    private val db: PositionDb,
    private val api: PositionApi
): RemoteMediator<Int, PositionEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PositionEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
//                        (lastItem.id.hashCode() / state.config.pageSize) + 1
                        state.config.pageSize + 1
                    }
                }
            }

            val positions = api.getPositions(
                page = loadKey,
                pageCount = state.config.pageSize
            )
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.clearAll()
                }
                val entities = positions.map {
                    it.toPositionEntity()
                }
                db.dao.upsertAll(entities)
            }
            return MediatorResult.Success(endOfPaginationReached = positions.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}