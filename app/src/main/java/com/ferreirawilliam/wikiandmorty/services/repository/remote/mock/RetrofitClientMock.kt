package com.ferreirawilliam.wikiandmorty.services.repository.remote.mock

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientMock private constructor(){
    companion object{

        private lateinit var retrofit: Retrofit
        private val baseUrl = SERVER_DATA.URL

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.SECONDS)
            if (!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder().baseUrl(baseUrl)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>):S{
            return getRetrofitInstance().create(serviceClass)
        }
    }

    object SERVER_DATA{
        const val URL = "https://mock_rick_and_morty/"
        const val PORT = 5000
    }
}