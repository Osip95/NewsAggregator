package com.example.newsfeatcherdz.feature.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsfeatcherdz.base.BaseViewModel
import com.example.newsfeatcherdz.base.Event
import com.example.newsfeatcherdz.base.SingleLiveEvent
import com.example.newsfeatcherdz.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfeatcherdz.feature.domain.ArticleModel
import com.example.newsfeatcherdz.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(private val interactor: ArticlesInteractor, private val bookmarksInteractor: BookmarksInteractor): BaseViewModel<ViewState>() {

    private val _goNewsEvent = SingleLiveEvent<ArticleModel>()
    val goNewsEvent: LiveData<ArticleModel> = _goNewsEvent
    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState(): ViewState = ViewState(
        articleList = emptyList(),
        articlesShown = emptyList(),
        isSearchEnabled = false)

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
          is DataEvent.LoadArticles -> {
            viewModelScope.launch {
                interactor.getArticles().fold(
                    onError = {
                        Log.e("ERROR",it.localizedMessage )
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                    }
                )
            }
              return null
          }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(articleList = event.articles, articlesShown = event.articles )
            }


            is UiEvent.OnArticleClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                _goNewsEvent.value = previousState.articlesShown[event.index]

                return null
            }

            is UiEvent.OnSearchButtonClicked -> {
                return previousState.copy(articlesShown = if(previousState.isSearchEnabled) previousState.articleList
                else previousState.articlesShown,
                    isSearchEnabled = !previousState.isSearchEnabled)
            }

            is UiEvent.OnSearchTextEdited -> {
                return previousState.copy(articlesShown = previousState.articleList.filter { it.title.contains(event.text) })
            }
            else -> return null
        }
    }
}