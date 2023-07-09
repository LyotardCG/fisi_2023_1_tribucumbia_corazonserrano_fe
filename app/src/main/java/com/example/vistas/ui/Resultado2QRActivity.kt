package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.vistas.R
import com.example.vistas.databinding.FrMenuPrincipalEmpleadosBinding

class Resultado2QRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_inexistente)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        btnVolver1.setOnClickListener {
            val intent = Intent(applicationContext, Inicio::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}