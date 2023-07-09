package com.example.vistas.model

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class DetalleViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val nombrePLatillo = view.findViewById<TextView>(R.id.tvNombrePlatillo)
    val cantidad = view.findViewById<TextView>(R.id.tvCantidadPlatillo)


    fun render(detalleModel : ReservaCartaDetalle){
        nombrePLatillo.text = detalleModel.nombre
        cantidad.text = "x${detalleModel.cantidad.toString()}"
    }
}