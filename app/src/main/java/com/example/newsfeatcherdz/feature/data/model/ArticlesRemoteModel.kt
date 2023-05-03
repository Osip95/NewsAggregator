package com.example.newsfeatcherdz.feature.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesRemoteModel(
    @SerializedName("articles")
    val articleList: List<ArticleRemouteModel>
)
