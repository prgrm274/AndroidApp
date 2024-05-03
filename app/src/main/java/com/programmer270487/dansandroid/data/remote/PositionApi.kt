package com.programmer270487.dansandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PositionApi {
    @GET("positions.json/")
    suspend fun getPositions(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int,
    ): List<PositionDto>

    @GET("positions/{ID}")
    suspend fun searchPositions(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int,
    ): List<PositionDto>

    companion object {
        const val BASE_URL = "https://dev6.dansmultipro.com/api/recruitment/"
    }
}