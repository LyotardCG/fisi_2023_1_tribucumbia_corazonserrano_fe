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
    //val fotoSede = view.findViewById<ImageView>(R.id.imageViewSede1)




    fun render(SedeModel : Sede){

        view.setOnClickListener{listener.onClick(
            SedeModel.idSede,
            SedeModel.nombre
        )}

        nombreSede.text = SedeModel.nombre
        ubicacionSede.text = SedeModel.direccion

        //val base64String = SedeModel.foto
        //val base64Image = base64String.split(",").toTypedArray()[1]

        //val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        //val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        val fotoSede = view.findViewById<ImageView>(R.id.imageViewSede1)

        when (SedeModel.idSede) {
            1 -> fotoSede.setImageResource(R.drawable.sede1)
            2 -> fotoSede.setImageResource(R.drawable.sede2)
            3 -> fotoSede.setImageResource(R.drawable.sede3)
            // Agrega más casos según sea necesario para cada ID de sede
            else -> {
                // Si el ID de sede no coincide con ninguno de los casos anteriores, puedes manejarlo de alguna manera predeterminada
                fotoSede.setImageResource(R.drawable.ic_launcher_background)
            }
        }

    }

}