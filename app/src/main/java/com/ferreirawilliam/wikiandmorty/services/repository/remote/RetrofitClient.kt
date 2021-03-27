package com.ferreirawilliam.wikiandmorty.services.repository.remote

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

class RetrofitClient private constructor(){

    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        val duration: Duration = Duration.ofMillis(5000)
        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://rickandmortyapi.com/api/"
        private fun getRetrofitInstance(): Retrofit{
            val httpClient = OkHttpClient.Builder().connectTimeout(duration)
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
}