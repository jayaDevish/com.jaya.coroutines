package com.jaya.newsapp.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getNews() = apiService.getNews()

    override suspend fun getMoreNews() = apiService.getMoreNews()

}