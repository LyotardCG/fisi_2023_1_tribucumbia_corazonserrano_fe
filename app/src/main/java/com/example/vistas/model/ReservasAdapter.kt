package com.example.vistas.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R

class ReservasAdapter(
    val reservasList : List<ReservacionCliente>,
    val itemClickListener : OnReservaClickListener
) : RecyclerView.Adapter<ReservasViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReservasViewHolder(
            layoutInflater.inflate(R.layout.item_reserva,parent,false),
            this.itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ReservasViewHolder, position: Int) {
        val item = reservasList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return reservasList.size
    }
}