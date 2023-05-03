package com.example.newsfeatcherdz.feature.articledetailsscreen.ui


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsfeatcherdz.R
import com.example.newsfeatcherdz.shared.BottomMenuController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {


    private val articleDetailsScreenViewModel: ArticleDetailsScreenViewModel by viewModel {
        parametersOf(
            arguments?.getString(TITLE_KEY) ?: NOT_FOUND,
            arguments?.getString(DESCRIPTION_KEY) ?: NOT_FOUND,
            arguments?.getString(URL_IMAGE_KEY) ?: NOT_FOUND
        )
    }
    private val bottomMenuController: BottomMenuController by lazy { requireActivity() as BottomMenuController }
    private val tvDescription: TextView by lazy { requireActivity().findViewById(R.id.tvNews) }
    private val imgView: ImageView by lazy { requireActivity().findViewById(R.id.filmPreviewImageView) }
    private val tvToolbar: TextView by lazy { requireActivity().findViewById(R.id.custom_title) }
    private val btnClose: ImageButton by lazy { requireActivity().findViewById(R.id.btnClose) }

    companion object {
        private const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        private const val URL_IMAGE_KEY = "URL_IMAGE_KEY"
        private const val TITLE_KEY = "TITLE_KEY"
        private const val NOT_FOUND = "NOT_FOUND"
        fun newInstance(
            description: String,
            urlImage: String,
            title: String
        ): ArticleDetailsFragment {
            val bundle = Bundle().apply {
                putString(DESCRIPTION_KEY, description)
                putString(URL_IMAGE_KEY, urlImage)
                putString(TITLE_KEY, title)
            }
            return ArticleDetailsFragment().apply { arguments = bundle }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomMenuController.hideBottomNavigationMenu()
        articleDetailsScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)

        btnClose.setOnClickListener {
            closeFragment()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            closeFragment()
        }
    }

    private fun closeFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        bottomMenuController.showBottomNavigationMenu()
    }

    private fun render(viwState: ViewState) {
        tvDescription.text = viwState.description
        tvToolbar.text = viwState.title
        Glide
            .with(this)
            .load(viwState.urlImage)
            .into(imgView)
    }
    
}