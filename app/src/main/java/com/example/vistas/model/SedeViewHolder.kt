package com.example.vistas.model

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class SedeViewHolder(val view : View,val listener : OnSedeClickListener) : RecyclerView.ViewHolder(view){

    val nombreSede = view.findViewById<TextView>(R.id.textViewNombreSede1)
    val ubicacionSede = view.findViewById<TextView>(R.id.textViewNombreDireccion1)
    val fotoSede = view.findViewById<ImageView>(R.id.imageViewSede1)




    fun render(SedeModel : Sede){

        view.setOnClickListener{listener.onClick(
            SedeModel.id_sede,
            SedeModel.sede
        )}

        nombreSede.text = SedeModel.sede
        ubicacionSede.text = SedeModel.ubicacion

        val base64String = SedeModel.foto
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        fotoSede.setImageBitmap(decodedByte)

    }

}