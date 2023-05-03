package com.example.newsfeatcherdz.feature.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleModel (
    val author:String,
    val title:String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
        )