package com.example.newsfeatcherdz

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsfeatcherdz.feature.bookmarks.data.local.BookmarksDao
import com.example.newsfeatcherdz.feature.bookmarks.data.local.model.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 2)
abstract class AppDataBase: RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao

}