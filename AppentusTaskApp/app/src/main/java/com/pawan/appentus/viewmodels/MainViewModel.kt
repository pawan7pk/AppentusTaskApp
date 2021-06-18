package com.pawan.appentus.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pawan.appentus.data_class.ResponseData
import com.pawan.appentus.repository.AuthorRepository
import com.pawan.appentus.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel : ViewModel() {

    val liveDataResponse = MutableLiveData<ResponseStatus<ResponseData>>()

    fun callPicsumApi() {
        liveDataResponse.postValue(ResponseStatus.Loader(true))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = AuthorRepository().callPicSumApi(34)
                withContext(Dispatchers.Main) {
                    handleResponse(response)
                }
            } catch (e: Exception) {

            } finally {
                liveDataResponse.postValue(ResponseStatus.Loader(false))

            }
        }
    }

    private fun handleResponse(response: Response<ResponseData>) {

        when {
            response.isSuccessful && response.body() != null -> {
                val authorResponse = response.body()
                authorResponse?.let {
                    Log.i("log", "Response: ${Gson().toJson(response.body())}")
                    liveDataResponse.postValue(ResponseStatus.Success(it))
                }
            }
            else ->
                liveDataResponse.postValue(ResponseStatus.Error("Something went wrong"))
        }
    }
}