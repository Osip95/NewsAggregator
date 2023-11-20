package com.example.newsfeatcherdz.feature.bookmarks.ui


import com.example.newsfeatcherdz.base.Event
import com.example.newsfeatcherdz.feature.domain.ArticleModel

data class ViewState(
    val bookmarksArticle: List<ArticleModel>
)

sealed class UiEvent : Event {
    data class OnArticleClicked(val index: Int): UiEvent()
}
sealed class DataEvent : Event {
    object LoadBookmarks : DataEvent()
    data class OnSuccessBookmarksLoaded(val bookmarksArticle: List<ArticleModel>) : DataEvent()
    data class OnFailedBookmarksLoaded(val throwable: Throwable) : DataEvent()
}