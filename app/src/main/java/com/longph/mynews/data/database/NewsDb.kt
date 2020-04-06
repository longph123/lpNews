package com.longph.mynews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.longph.domain.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDb : RoomDatabase() {

    companion object {
        fun create(context: Context): NewsDb {
            val databaseBuilder = Room.databaseBuilder(context, NewsDb::class.java, "newsdb.db")
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun newsFeedDao(): NewsFeedDao
}