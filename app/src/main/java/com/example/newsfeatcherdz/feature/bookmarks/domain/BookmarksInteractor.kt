package com.example.newsfeatcherdz.feature.bookmarks.domain

import com.example.newsfeatcherdz.base.Either
import com.example.newsfeatcherdz.base.attempt
import com.example.newsfeatcherdz.feature.bookmarks.data.local.BookmarksRepository
import com.example.newsfeatcherdz.feature.domain.ArticleModel

class BookmarksInteractor(private val bookmarksRepository: BookmarksRepository) {

    suspend fun create(model: ArticleModel) {
        attempt {  bookmarksRepository.create(model) }
    }

    suspend fun read(): Either<Throwable,List<ArticleModel>> {
        return attempt { bookmarksRepository.read()}

    }

    suspend fun update(model: ArticleModel) {
       attempt { bookmarksRepository.update(model) }
    }

    suspend fun delete(model: ArticleModel) {
       attempt { bookmarksRepository.delete(model) }
    }
}