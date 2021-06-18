package com.pawan.appentus.repository

import com.pawan.appentus.data_class.ResponseData
import com.pawan.appentus.network.RetrofitNetworking
import retrofit2.Response

class AuthorRepository {

    suspend fun callPicSumApi(size: Int): Response<ResponseData> =
        RetrofitNetworking.create().getPhotosList(size)
}