package com.example.bedpatient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ArcticleAdapter (val arcticleList: List<ArcticleModel>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemhorizontal, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = arcticleList[position]
        holder.textTitleItem.text =  dataModel.title
        Picasso.get().load(dataModel.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return arcticleList.size
    }

}
