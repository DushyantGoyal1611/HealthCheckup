package com.example.healthcheckup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthcheckup.databinding.ActivityCheckupAdapterBinding
import com.example.healthcheckup.model.Tests

class CheckupAdapter(val items:ArrayList<Tests>, val context: Context, val itemPosition:(Int)->Unit) : RecyclerView.Adapter<CheckupAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CheckupAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckupAdapter.ViewHolder {
        val binding = ActivityCheckupAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding:ActivityCheckupAdapterBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Tests){
            binding.textView5.text = item.name
            Glide.with(context).load(item.image).into(binding.imageView2)
            binding.root.setOnClickListener {
                itemPosition.invoke(adapterPosition)
            }
        }
    }

}