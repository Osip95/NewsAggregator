package com.example.newsfeatcherdz.feature.data.model

import com.example.newsfeatcherdz.feature.domain.ArticleModel

fun ArticleRemouteModel.toDomain() = ArticleModel(
    title = title,
    author = author ?: "",
    description = description ?: "",
    url = url,
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt
)