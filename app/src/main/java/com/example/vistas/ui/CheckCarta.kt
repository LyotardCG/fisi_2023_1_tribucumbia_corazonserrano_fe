package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.CartasApiService
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.ui.MainActivity.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckCarta : AppCompatActivity() {



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
        val intentInicio = Intent(this,Inicio::class.java)

        startActivity(intentInicio)
    }

    fun cargarInfo(){
        val usuario = findViewById<TextView>(R.id.textViewHolaUCarta)
        usuario.text = "USUARIO"
    }
}