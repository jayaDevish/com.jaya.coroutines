package com.jaya.newsapp.data.api

import com.jaya.newsapp.data.model.News
import retrofit2.http.GET

interface ApiService {

    @GET("news")
    suspend fun getNews(): List<News>

    @GET("more-news")
    suspend fun getMoreNews(): List<News>

}