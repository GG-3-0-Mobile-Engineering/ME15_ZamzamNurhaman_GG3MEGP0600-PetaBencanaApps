package com.example.petabencana.presentation.ui.reports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petabencana.R
import com.example.petabencana.databinding.CardReportBinding
import com.example.petabencana.domain.models.Report
import com.example.petabencana.utils.extensions.DateTimeFormat

class ReportsAdapter(private val list: List<Report>) : RecyclerView.Adapter<ReportsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding : CardReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(report: Report){
            binding.newsTitle.text = report.properties.title ?: "Bencana : -"
            binding.newsDescription.text = report.properties.text
            binding.reportDate.text = DateTimeFormat().convertDate(report.properties.createdAt)
            if(report.properties.imageURL.isNullOrEmpty()){
                binding.newsImage.setImageResource(R.drawable.no_image)
            }else{
                Glide.with(binding.root).load(report.properties.imageURL).into(binding.newsImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardReportBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}