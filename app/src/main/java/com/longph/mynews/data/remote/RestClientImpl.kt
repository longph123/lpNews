package com.longph.movieapp_mvvm.data

import com.google.gson.Gson
import com.longph.mynews.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClientImpl : RestClient {

    override fun <T>createRestApi(interfaceClass: Class<T>) : T{
        var gson = Gson().newBuilder()
                    .setPrettyPrinting()
                    .create()

        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(initDefaultHeader())
            .addInterceptor(httpLoggingInterceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        return retrofit.create(interfaceClass)
    }

    override fun initDefaultHeader() : Interceptor {
        var interceptor = Interceptor.invoke { chain ->
            var request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            chain.proceed(request)
        }

        return interceptor
    }
}