package com.jaya.newsapp.data.repository

import com.jaya.newsapp.data.api.ApiHelper

class NewsListRepositoryImpl(private val apiHelper: ApiHelper) : NewsListRepository {

    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()

}