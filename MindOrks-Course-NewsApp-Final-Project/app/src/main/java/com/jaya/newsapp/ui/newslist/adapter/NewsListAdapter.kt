package com.jaya.newsapp.ui.newslist.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaya.newsapp.R
import com.jaya.newsapp.data.model.News
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsListAdapter(
    private val newsList: ArrayList<News>
) : RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            itemView.textViewTitle.text = news.title
            itemView.textViewDescription.text = news.description
            itemView.textViewSource.text = news.source
            Glide.with(itemView.imageViewBanner.context)
                .load(news.imageUrl)
                .into(itemView.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(news.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsList[position])

    fun addData(list: List<News>) {
        newsList.addAll(list)
    }

}