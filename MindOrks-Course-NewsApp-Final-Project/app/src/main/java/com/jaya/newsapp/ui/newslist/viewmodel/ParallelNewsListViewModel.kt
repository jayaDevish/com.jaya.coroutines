package com.jaya.newsapp.ui.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.newsapp.data.model.News
import com.jaya.newsapp.data.repository.NewsListRepository
import com.jaya.newsapp.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ParallelNewsListViewModel(private val newsListRepository: NewsListRepository) : ViewModel() {

    private val newsList = MutableLiveData<Resource<List<News>>>()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsList.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    val responseDeferred = async { newsListRepository.getNews() }
                    val moreResponseDeferred = async { newsListRepository.getMoreNews() }

                    val response = responseDeferred.await()
                    val moreResponse = moreResponseDeferred.await()

                    val allResponse = mutableListOf<News>()
                    allResponse.addAll(response)
                    allResponse.addAll(moreResponse)

                    newsList.postValue(Resource.success(allResponse))
                }
            } catch (e: Exception) {
                newsList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getNews(): LiveData<Resource<List<News>>> {
        return newsList
    }

}