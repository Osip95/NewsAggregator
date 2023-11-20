package com.example.newsfeatcherdz.feature.data

import com.example.newsfeatcherdz.feature.data.model.ArticlesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query
private const val API_KEY = "26603c330a0c4bf2834324b44e8a63b9"
interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String = "us"
    ) : ArticlesRemoteModel

}