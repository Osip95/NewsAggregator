package com.example.newsfeatcherdz.feature.bookmarks.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeatcherdz.R
import com.example.newsfeatcherdz.feature.domain.ArticleModel
import com.example.newsfeatcherdz.shared.ArticlesAdapter
import com.example.newsfeatcherdz.feature.articledetailsscreen.ui.ArticleDetailsFragment

import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private val rvBookmarks: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookMarkedArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter { index ->
            viewModel.processUIEvent(
                UiEvent.OnArticleClicked(index)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        viewModel.goNewsEvent.observe(viewLifecycleOwner, ::goArticlesDetailsScreen)
        rvBookmarks.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksArticle)
    }

    private fun goArticlesDetailsScreen(articleModel: ArticleModel) {
        val articleDetailsFragment = ArticleDetailsFragment.newInstance(
            description = articleModel.description,
            urlImage = articleModel.urlToImage,
            title = articleModel.title
        )
        requireActivity().supportFragmentManager.beginTransaction().hide(this)
            .add(R.id.container, articleDetailsFragment).addToBackStack(null).commit()

    }
}