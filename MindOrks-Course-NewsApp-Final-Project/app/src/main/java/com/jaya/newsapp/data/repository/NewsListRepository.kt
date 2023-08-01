package com.jaya.newsapp.data.repository

import com.jaya.newsapp.data.model.News

interface NewsListRepository {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>

}