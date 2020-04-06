package com.longph.mynews.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    var loadingData = MutableLiveData<Boolean>().apply { value = false }
    var errorMessage = MutableLiveData<String>()
}