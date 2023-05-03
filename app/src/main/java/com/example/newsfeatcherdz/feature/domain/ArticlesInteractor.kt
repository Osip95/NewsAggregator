package com.example.newsfeatcherdz.feature.domain

import com.example.newsfeatcherdz.base.Either
import com.example.newsfeatcherdz.base.attempt
import com.example.newsfeatcherdz.feature.data.ArticlesRepository

class ArticlesInteractor(private val repository: ArticlesRepository) {

    suspend fun getArticles() = attempt{ repository.getArticles() }

}