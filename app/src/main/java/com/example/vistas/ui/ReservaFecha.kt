package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import com.example.vistas.R

class ReservaFecha : AppCompatActivity() {

    private var fechaReserva : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_mesa_fecha)
        cargarSede()

        val butContinuar = findViewById<Button>(R.id.buttonConfirmarMesaFecha)
        val calendar = findViewById<CalendarView>(R.id.calendarView2)
        calendar.setOnDateChangeListener{view,year,month,dayOfMonth ->
            val selectDate = "$year-${month+1}-$dayOfMonth"
            this.fechaReserva = selectDate
        }
        butContinuar.setOnClickListener {
            cargarFecha()
        }
    }

    fun cargarSede(){
        val tvSede = findViewById<TextView>(R.id.tvSede)
        tvSede.text = "Sede : ${intent.getStringExtra("sede")}"
    }

    fun cargarFecha(){
        println(this.fechaReserva)
        val id_sede = intent.getIntExtra("id_sede",0)
        val sede = intent.getStringExtra("sede")

        val intentHora = Intent(this,ReservaHora::class.java)

        intentHora.putExtra("id_sede",id_sede)
        intentHora.putExtra("sede",sede)
        intentHora.putExtra("fecha",this.fechaReserva)
        startActivity(intentHora)
    }
}