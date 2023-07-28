package com.example.petabencana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petabencana.R
import com.example.petabencana.model.News

class NewsAdapter(private val context: Context?) : RecyclerView.Adapter<NewsAdapter.NewsCardAdapter>() {

    fun getData() : List<News>{
        return List<News>(20){
            News(
                imageResourceId = R.drawable.ic_launcher_foreground,
                title = "BENCANA TERKINI",
                description = "sdfkjdsklfj dslkfjlksdjfk ldskfjlkdsjgkldsjkgjskdlj kdjlkgsdjlgjskdlgjd"
            )
        }
    }

    val dataset = getData()

    class NewsCardAdapter(view: View?) : RecyclerView.ViewHolder(view!!) {
        val imageView = view!!.findViewById<ImageView>(R.id.news_image)
        val titleNews = view!!.findViewById<TextView>(R.id.news_title)
        val descriptionNews = view!!.findViewById<TextView>(R.id.news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardAdapter {
        val adapterLayout = LayoutInflater.from(parent.context)
        var viewHolderAdapter: View? = adapterLayout.inflate(R.layout.card_news, parent, false)
        return NewsCardAdapter(viewHolderAdapter)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: NewsCardAdapter, position: Int) {
        val data = dataset[position]
//        val resources = context?.resources
        holder.imageView.setImageResource(data.imageResourceId)
        holder.titleNews.text = data.title
        holder.descriptionNews.text = data.description
    }
}