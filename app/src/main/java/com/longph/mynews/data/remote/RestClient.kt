package com.longph.movieapp_mvvm.data

import okhttp3.Interceptor

interface RestClient {

    fun <T> createRestApi(interfaceClass: Class<T>): T
    fun initDefaultHeader(): Interceptor

}