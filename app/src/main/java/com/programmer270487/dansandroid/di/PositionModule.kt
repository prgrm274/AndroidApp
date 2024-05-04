package com.programmer270487.dansandroid.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.programmer270487.dansandroid.data.local.PositionDb
import com.programmer270487.dansandroid.data.local.PositionEntity
import com.programmer270487.dansandroid.data.remote.PositionApi
import com.programmer270487.dansandroid.data.remote.PositionRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePositionDatabase(@ApplicationContext context: Context): PositionDb {
        return Room.databaseBuilder(
            context,
            PositionDb::class.java,
            "positions.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePositionApi(): PositionApi {
        return Retrofit.Builder()
            .baseUrl(PositionApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePositionPager(db: PositionDb, api: PositionApi): Pager<Int, PositionEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10), // 20
            remoteMediator = PositionRemoteMediator(
                db = db,
                api = api
            ),
            pagingSourceFactory = {
                db.dao.pagingSource()
            }
        )
    }
}