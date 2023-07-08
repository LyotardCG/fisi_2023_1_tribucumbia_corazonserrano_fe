/* Funcionalidad de vista fr_reserva_inexistente */
package com.example.escaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Result2QRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_inexistente)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        btnVolver1.setOnClickListener { onBackPressed() }
    }
}