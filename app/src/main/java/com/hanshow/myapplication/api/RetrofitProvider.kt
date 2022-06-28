package com.hanshow.myapplication.api

import com.hanshow.myapplication.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
object RetrofitProvider {
    val marsApi: Api
    val client: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
            .connectTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.SECONDS)

        client = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://117.50.174.22")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        marsApi = retrofit.create(Api::class.java)
    }

}