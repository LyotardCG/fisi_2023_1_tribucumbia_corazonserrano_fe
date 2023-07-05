package com.example.vistas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vistas.R
import com.example.vistas.model.Platillo
import com.example.vistas.model.PlatilloFinal
import com.example.vistas.model.ReservaCarta

class Final : AppCompatActivity() {

    private var lista : MutableList<PlatilloFinal> = mutableListOf()
    private var reserva : ReservaCarta = ReservaCarta(lista)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_carta_adicional)
        cargarLista()
    }

    fun cargarLista(){

        val id_platillo = intent.getIntExtra("id_platillo",0)
        val nombre = intent.getStringExtra("nombre").toString()
        val precio = intent.getFloatExtra("precio",0.0f)
        val foto = intent.getStringExtra("foto").toString()
        val cantidad = intent.getIntExtra("Cantidad",0)

    }
}