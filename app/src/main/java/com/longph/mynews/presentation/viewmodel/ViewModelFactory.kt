package com.longph.mynews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.longph.domain.GetNewsUseCase
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory <VM: ViewModel> @Inject constructor( val getNewsUseCase: GetNewsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetNewsUseCase::class.java).newInstance(getNewsUseCase)
    }
}