package com.example.vistas.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class PlatilloAdapter(
    val platillosList : List<Platillo>,
    val itemClickListener : OnClickPlatillo
    ) : RecyclerView.Adapter<PlatilloViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlatilloViewHolder(
            layoutInflater.inflate(R.layout.item_platillo,parent,false),
            this.itemClickListener
        )
    }

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
       val item = platillosList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return platillosList.size
    }
}