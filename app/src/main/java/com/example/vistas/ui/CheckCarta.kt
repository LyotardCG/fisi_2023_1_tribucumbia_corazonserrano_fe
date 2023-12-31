package com.example.vistas.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.vistas.R
import com.example.vistas.model.PlatilloFinal
import com.example.vistas.util.Global
import com.example.vistas.util.Prefs

class CheckCarta : AppCompatActivity() {
    companion object{
        lateinit var prefs: Prefs
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = Prefs(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_carta_confirmada)
        val buttonMesaConfInicio = findViewById<Button>(R.id.buttonCartaConfInicio)
        cargarInfo()
        buttonMesaConfInicio.setOnClickListener {
            gotoInicio()
        }

    }



    fun gotoInicio(){
        Global.platillosSeleccionados = ArrayList<PlatilloFinal>()
        val intentInicio = Intent(this,Inicio::class.java)
        val reservacion = intent.getStringExtra("id_reservacion")
        intentInicio.putExtra("id_reservacion",reservacion)
        startActivity(intentInicio)
    }

    fun cargarInfo(){
        val usuario = findViewById<TextView>(R.id.textViewHolaUCarta2)
        usuario.text = "¡Completado!"


        val sede = findViewById<TextView>(R.id.finalsede)
        val sillas = findViewById<TextView>(R.id.finalsillas)
        val fecha = findViewById<TextView>(R.id.finalfecha)

        sede.text = Global.sede
        sillas.text =  Global.sillas
        fecha.text =  Global.fecha

        val qr_prueba=findViewById<ImageView>(R.id.imageViewImagenConfirmacionCarta)

        //Poniendo el QR

        val base64String = prefs.getQR()
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        qr_prueba.setImageBitmap(decodedByte)

    }
}