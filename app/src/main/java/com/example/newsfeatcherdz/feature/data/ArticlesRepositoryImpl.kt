package com.example.newsfeatcherdz.feature.data


import com.example.newsfeatcherdz.feature.data.model.toDomain
import com.example.newsfeatcherdz.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val source: ArticlesRemouteSource ): ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }.sortedBy { it.publishedAt }
    }
}