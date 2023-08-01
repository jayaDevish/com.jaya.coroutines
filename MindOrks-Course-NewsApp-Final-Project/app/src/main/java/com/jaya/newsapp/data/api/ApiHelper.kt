package com.jaya.newsapp.data.api

import com.jaya.newsapp.data.model.News

interface ApiHelper {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>

}