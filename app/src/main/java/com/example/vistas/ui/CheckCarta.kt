package com.example.vistas.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.CartasApiService
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.model.PlatilloFinal
import com.example.vistas.ui.MainActivity.Companion.prefs
import com.example.vistas.util.Global
import com.example.vistas.util.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckCarta : AppCompatActivity() {
    companion object{
        lateinit var prefs: Prefs
    }


    override fun onCreate(savedInstanceState: Bundle?) {
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
        val usuario = findViewById<TextView>(R.id.textViewHolaUCarta)
        usuario.text = "USUARIO"


        val sede = findViewById<TextView>(R.id.finalsede)
        val sillas = findViewById<TextView>(R.id.finalsillas)
        val fecha = findViewById<TextView>(R.id.finalfecha)


        //val prueba=findViewById<ImageView>(R.id.imageViewImagenConfirmacion)


        sede.text = Global.sede
        sillas.text =  Global.sillas
        fecha.text =  Global.fecha


        //val prueba=findViewById<ImageView>(R.id.imageViewImagenConfirmacionCarta)

        //Poniendo el QR
/*
        val base64String = prefs.getQR()
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        prueba.setImageBitmap(decodedByte)*/

    }
}