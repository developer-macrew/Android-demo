package com.example.dashboard.ui.fragments.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard.databinding.AdapterChatItemBinding
import com.example.dashboard.ui.fragments.home.model.Data

class ChatAdapterAdapter :  RecyclerView.Adapter<ChatAdapterAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: AdapterChatItemBinding):RecyclerView.ViewHolder(binding.root)

    private var list= ArrayList<Data>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list :ArrayList<Data>){
        this.list=list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdapterChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            data=list[position]
        }
    }


}