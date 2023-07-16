package com.example.vistas.model


import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class ReservasViewHolder(val view : View,val listener : OnReservaClickListener) : RecyclerView.ViewHolder(view){

    val cod_reserva = view.findViewById<TextView>(R.id.cod_reserva)
    val fecha_reserva = view.findViewById<TextView>(R.id.TextViewDataHoraReserva)
    val atentido_reserva = view.findViewById<TextView>(R.id.textViewDataFechaReserva)

    val btnDetallar =  view.findViewById<Button>(R.id.detalla_reserva)




    fun render(reservasModel: ReservacionCliente){


        btnDetallar.setOnClickListener { listener.onClickDetallar(reservasModel.idReservacion,reservasModel.horario,reservasModel.nombre,reservasModel.numeroSillas,reservasModel.qr) }

        cod_reserva.text = "#${reservasModel.idReservacion}"
        fecha_reserva.text=reservasModel.horario

        if(reservasModel.atendido == 1){
            atentido_reserva.text = "Atendido"
        }else{
            atentido_reserva.text = "Pendiente"
        }
    }
}