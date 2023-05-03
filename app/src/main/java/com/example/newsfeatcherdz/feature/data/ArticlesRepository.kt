package com.example.newsfeatcherdz.feature.data

import com.example.newsfeatcherdz.feature.domain.ArticleModel

interface ArticlesRepository {

    suspend fun getArticles(): List<ArticleModel>

}