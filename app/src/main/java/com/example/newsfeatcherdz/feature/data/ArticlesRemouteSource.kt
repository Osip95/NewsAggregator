package com.example.newsfeatcherdz.feature.data

import com.example.newsfeatcherdz.feature.data.model.ArticlesRemoteModel

class ArticlesRemouteSource(private val api: NewsApi) {

    suspend fun getArticles(): ArticlesRemoteModel {
        return api.getArticles()
    }
}