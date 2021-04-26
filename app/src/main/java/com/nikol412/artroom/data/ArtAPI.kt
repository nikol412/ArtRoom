package com.nikol412.artroom.data

import com.google.gson.Gson
import com.nikol412.artroom.data.response.ArtWorkData
import com.nikol412.artroom.data.response.ArtworkResponse
import com.nikol412.artroom.data.response.ArtworksListResponse
import com.nikol412.artroom.data.response.SearchResponse
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


object ArtAPI {
    fun getApi(): API {
        return Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(getOkhttpClient())
            .build()
            .create(API::class.java)
    }

    fun getSearchApi(): API {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()
            .create(API::class.java)
    }

    private fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val importantFieldsIntercepter = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(
                        "fields",
                        "id,api_link,title,date_display,artist_title,dimensions,artist_ids,image_id"
                    )
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(importantFieldsIntercepter)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}

interface API {
    @GET("artworks/{id}")
    suspend fun getArtworks(@Path("id") id: Int): ArtworkResponse

    @GET("artworks")
    suspend fun getArtsPage(@Query("page") page: Int): ArtworksListResponse

    @GET("artworks/search")
    suspend fun searchArtwork(@Query("q") query: String, @Query("limit") limit: Int = 20): SearchResponse
}