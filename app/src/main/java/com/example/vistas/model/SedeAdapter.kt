package com.example.vistas.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class SedeAdapter(
    val sedesList : List<Sede>,
    val itemClickListener : OnSedeClickListener
) : RecyclerView.Adapter<SedeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SedeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SedeViewHolder(
            layoutInflater.inflate(R.layout.item_sede,parent,false),
            this.itemClickListener
        )
    }

    override fun onBindViewHolder(holder: SedeViewHolder, position: Int) {
        val item = sedesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return sedesList.size
    }
}