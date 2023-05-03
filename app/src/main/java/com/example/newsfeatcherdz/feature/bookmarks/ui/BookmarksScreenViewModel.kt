package com.example.newsfeatcherdz.feature.bookmarks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsfeatcherdz.base.BaseViewModel
import com.example.newsfeatcherdz.base.Event
import com.example.newsfeatcherdz.base.SingleLiveEvent
import com.example.newsfeatcherdz.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfeatcherdz.feature.domain.ArticleModel
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor): BaseViewModel<ViewState>() {
    private val _goNewsEvent = SingleLiveEvent<ArticleModel>()
    val goNewsEvent: LiveData<ArticleModel> = _goNewsEvent
   init {
       processDataEvent(DataEvent.LoadBookmarks)
   }

    override fun initialViewState(): ViewState = ViewState(bookmarksArticle = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
           is DataEvent.LoadBookmarks -> {
              viewModelScope.launch {
                  interactor.read().fold(
                      onError = {},
                      onSuccess = {
                          processDataEvent(DataEvent.OnSuccessBookmarksLoaded(it))

                      }

                  )
              }
               return null

            }
            is DataEvent.OnSuccessBookmarksLoaded -> {
                return previousState.copy(bookmarksArticle = event.bookmarksArticle)

            }
            is UiEvent.OnArticleClicked ->{
                _goNewsEvent.value=previousState.bookmarksArticle[event.index]
                return null
            }
            else -> return null
        }
    }
}