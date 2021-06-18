package com.pawan.appentus.network

import com.pawan.appentus.data_class.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallInterface {

    @GET("/v2/list")
    suspend fun getPhotosList(
        @Query("size") size: Int
    ): Response<ResponseData>
}