package com.example.newsfeatcherdz.feature.di

import com.example.newsfeatcherdz.feature.data.ArticlesRemouteSource
import com.example.newsfeatcherdz.feature.data.ArticlesRepository
import com.example.newsfeatcherdz.feature.data.ArticlesRepositoryImpl
import com.example.newsfeatcherdz.feature.data.NewsApi
import com.example.newsfeatcherdz.feature.domain.ArticlesInteractor
import com.example.newsfeatcherdz.feature.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModule = module {
    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single<ArticlesRemouteSource> {
        ArticlesRemouteSource(api = get())
    }

    single<ArticlesRepository> {
        ArticlesRepositoryImpl(source = get())
    }

    single<ArticlesInteractor> {
        ArticlesInteractor(repository = get())
    }

    viewModel {
        MainScreenViewModel(interactor = get(), bookmarksInteractor = get())
    }
}