package com.example.vistas.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.response.ReservasCartasDetallesResponse


class DetalleAdapter(val platillosList : List<ReservaCartaDetalle>) :
    RecyclerView.Adapter<DetalleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetalleViewHolder(layoutInflater.inflate(R.layout.item_detalle,parent,false))
    }

    override fun onBindViewHolder(holder:DetalleViewHolder, position: Int) {
        val item = platillosList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return platillosList.size
    }
}