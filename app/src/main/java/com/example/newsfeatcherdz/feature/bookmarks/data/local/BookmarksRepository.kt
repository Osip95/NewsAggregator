package com.example.newsfeatcherdz.feature.bookmarks.data.local

import com.example.newsfeatcherdz.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfeatcherdz.feature.domain.ArticleModel

interface BookmarksRepository {
   suspend fun create(model: ArticleModel)

   suspend fun read(): List<ArticleModel>

   suspend fun update(model: ArticleModel)

  suspend  fun delete(model: ArticleModel)
}