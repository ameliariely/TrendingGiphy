package com.ameliariely.trendinggiphy.data.remote

import com.ameliariely.trendinggiphy.data.remote.model.GiphyResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    fun getGifTrendingResponse(@Query("offset") offset : Int): Single<GiphyResponse>

    @GET("gifs/search")
    fun getGifSearchList(@Query("q") query: String, @Query("offset") offset : Int): Single<GiphyResponse>
}

class GiphyClient(private val token: String) {

    fun getGiphyClient(): GiphyApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        //Create OkHTTP Client to add custom interceptor for logging and authentication
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor({ chain -> intercept(chain) })
                .addInterceptor(logging)
                .build()

        //Create Retrofit instance with GSON converter and RxJava adapter
        val retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(GiphyApi::class.java)
    }

    private val baseUrl = "https://api.giphy.com/v1/"

    private fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
                .header("api-key", token)

        val request = builder.build()
        return chain.proceed(request)
    }
}
