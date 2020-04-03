package com.longph.mynews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.longph.domain.GetNewsUseCase
import com.longph.domain.News
import com.longph.mynews.data.remote.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsFeedViewModel(val getNewsUseCase: GetNewsUseCase) : BaseViewModel(){

    var getNewsListLiveData : MutableLiveData<List<News>> = MutableLiveData()
    var coroutineJob : Job? = null

    fun getNewsList(){
        loadingData.value = true
        coroutineJob = CoroutineScope(Dispatchers.IO).launch {
            var apiResponse = getNewsUseCase.getNewsList()
            loadingData.postValue(false)
            when(apiResponse){
                is ApiResponse.Success -> getNewsListLiveData.postValue(apiResponse.result?.items)
                is ApiResponse.Error -> errorMessage.postValue(apiResponse.errorMessage)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        coroutineJob?.cancel()
    }
}