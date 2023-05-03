package com.example.newsfeatcherdz.feature.bookmarks.data

import com.example.newsfeatcherdz.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfeatcherdz.feature.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
title = title,
    description = description,
    url = url,
    author = author,
    urlToImage = urlToImage,
    publishedAt = publishedAt

)

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title,
    description = description,
    url = url,
    author = author,
    urlToImage = urlToImage,
    publishedAt = publishedAt

)