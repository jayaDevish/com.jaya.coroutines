package com.jaya.newsapp.ui.newslist.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaya.newsapp.R
import com.jaya.newsapp.data.api.ApiHelperImpl
import com.jaya.newsapp.data.api.RetrofitBuilder
import com.jaya.newsapp.data.model.News
import com.jaya.newsapp.ui.base.ViewModelFactory
import com.jaya.newsapp.ui.newslist.adapter.NewsListAdapter
import com.jaya.newsapp.ui.newslist.viewmodel.NewsListViewModel
import com.jaya.newsapp.utils.Status
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        newsListViewModel.getNews().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { newsList -> renderList(newsList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(newsList: List<News>) {
        adapter.addData(newsList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        newsListViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(NewsListViewModel::class.java)
    }
}
