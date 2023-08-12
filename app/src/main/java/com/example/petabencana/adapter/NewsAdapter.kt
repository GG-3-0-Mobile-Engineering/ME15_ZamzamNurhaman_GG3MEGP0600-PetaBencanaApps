package com.example.petabencana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petabencana.R
import com.example.petabencana.api.Feature

class ReportsAdapter(private val list: ArrayList<Feature>) : RecyclerView.Adapter<ReportsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val imageView = view!!.findViewById<ImageView>(R.id.news_image)
        val titleNews = view!!.findViewById<TextView>(R.id.news_title)
        val descriptionNews = view!!.findViewById<TextView>(R.id.news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        val viewHolderAdapter: View? = adapterLayout.inflate(R.layout.card_news, parent, false)
        return ItemViewHolder(viewHolderAdapter)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
//        val resources = context?.resources
//        holder.imageView.setImageResource(data.imageResourceId)
        holder.titleNews.text = data.properties.disasterType
        holder.descriptionNews.text = data.properties.text
    }
}