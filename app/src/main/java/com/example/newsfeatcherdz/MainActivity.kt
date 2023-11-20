package com.example.newsfeatcherdz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.example.newsfeatcherdz.feature.bookmarks.ui.BookmarksFragment
import com.example.newsfeatcherdz.feature.mainscreen.MainScreenFragment
import com.example.newsfeatcherdz.shared.BottomMenuController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomMenuController {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }
    private var selectedTabId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationMenu.setOnItemSelectedListener {
            when(it.itemId){
                selectedTabId -> Unit
                R.id.itemMain -> {
                    selectTab(MainScreenFragment())
                    selectedTabId = it.itemId
                }
                R.id.itemBookmarks ->{
                    selectTab(BookmarksFragment())
                    selectedTabId = it.itemId
                }
                else -> {}
            }
            true
        }
        bottomNavigationMenu.selectedItemId = R.id.itemMain
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }

    override fun hideBottomNavigationMenu() {
        bottomNavigationMenu.visibility = GONE
    }

    override fun showBottomNavigationMenu() {
        bottomNavigationMenu.visibility = VISIBLE
    }
}