package com.longph.mynews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.longph.domain.News

@Dao
interface NewsFeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(posts: List<News>)

    @Query("SELECT * FROM news")
    fun getNewsList(): List<News>

    @Query("DELETE FROM news")
    fun deleteAllNews()
}