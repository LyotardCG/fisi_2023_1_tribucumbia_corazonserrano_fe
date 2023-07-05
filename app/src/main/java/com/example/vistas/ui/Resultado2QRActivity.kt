package com.example.scannerqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Resultado2QRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_mesa_hora)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        btnVolver1.setOnClickListener { onBackPressed() }
    }
}