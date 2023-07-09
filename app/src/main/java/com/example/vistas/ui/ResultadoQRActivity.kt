package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import com.example.vistas.R
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.ReservasDetallesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultadoQRActivity : AppCompatActivity() {
    lateinit var txtResultado: TextView
    private val reservacionesApiService: ReservacionesApiService by lazy {
        ReservacionesApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_encontrada)
        //txtResultado = findViewById(R.id.cate_plat)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        getAndShowName()
        btnVolver1.setOnClickListener {
            val intent = Intent(applicationContext, Inicio::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun getAndShowName() {
        val bundle = intent.extras
        val name = bundle?.getString("INTENT_NAME")
        //txtResultado.text = name

        val sharedPreferences = getSharedPreferences("ReservaData", MODE_PRIVATE)
        val reservaId = sharedPreferences.getInt("reservaId", 0)
        val usuario = sharedPreferences.getString("usuario", "")
        val numeroSillas = sharedPreferences.getInt("numeroSillas", 0)
        val sede = sharedPreferences.getString("sede", "")
        val horario = sharedPreferences.getString("horario", "")


        val textViewIDReserva = findViewById<TextView>(R.id.textViewCodDetReserva)
        val textViewNomDetReserva = findViewById<TextView>(R.id.textViewNomDetReserva)
        val codReserva = findViewById<TextView>(R.id.cod_reserva)
        val codReserva3 = findViewById<TextView>(R.id.cod_reserva3)
        val codReserva2 = findViewById<TextView>(R.id.cod_reserva2)
        val TextViewDataHoraReserva = findViewById<TextView>(R.id.TextViewDataHoraReserva)
        val textViewDataFechaReserva = findViewById<TextView>(R.id.textViewDataFechaReserva)
        val textViewSillas = findViewById<TextView>(R.id.textViewSillas)

        textViewIDReserva.text = "#${reservaId}"
        textViewNomDetReserva.text = usuario
        codReserva.text = "Fecha y hora"
        codReserva3.text = "Local"
        codReserva2.text = "Sillas"
        TextViewDataHoraReserva.text = horario
        textViewDataFechaReserva.text = sede
        textViewSillas.text = numeroSillas.toString()
    }
}