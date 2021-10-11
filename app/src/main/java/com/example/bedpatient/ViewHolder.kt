package com.example.bedpatient

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (view : View) : RecyclerView.ViewHolder(view){

    var textTitleItem: TextView = view.findViewById(R.id.textTitleItem)
    var imageView: ImageView = view.findViewById(R.id.imageView)


}
