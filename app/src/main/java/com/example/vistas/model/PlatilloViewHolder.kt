package com.example.vistas.model

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class PlatilloViewHolder(val view : View,val listener:OnClickPlatillo) : RecyclerView.ViewHolder(view){

    val nombrePLatillo = view.findViewById<TextView>(R.id.tvNombrePlatillo)
    val categoriaPLatillo = view.findViewById<TextView>(R.id.tvCategoriaPlatillo)
    val descripcionPLatillo = view.findViewById<TextView>(R.id.tvDescripcionPlatillo)
    val precioPLatillo = view.findViewById<TextView>(R.id.tvPrecioPlatillo)
    val fotoPLatillo = view.findViewById<ImageView>(R.id.ivPlatillo)

    fun render(PlatilloModel : Platillo){

        view.setOnClickListener{
            listener.onClick(
                PlatilloModel.id_platillo,
                PlatilloModel.nombre,
                PlatilloModel.descripcion,
                PlatilloModel.foto,
                PlatilloModel.precio
            )
        }

        nombrePLatillo.text = PlatilloModel.nombre
        categoriaPLatillo.text = PlatilloModel.categoria
        descripcionPLatillo.text = PlatilloModel.descripcion
        precioPLatillo.text = PlatilloModel.precio.toString()

        val base64String = PlatilloModel.foto
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        fotoPLatillo.setImageBitmap(decodedByte)

    }

}