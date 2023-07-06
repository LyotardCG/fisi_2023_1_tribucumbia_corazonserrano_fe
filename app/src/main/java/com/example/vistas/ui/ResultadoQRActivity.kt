package com.example.scannerqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button


class ResultadoQRActivity : AppCompatActivity() {
    //@SuppressLint("WrongViewCast")

    lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_encontrada)
        txtResultado = findViewById(R.id.txtResultado)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        getAndShowName()
        btnVolver1.setOnClickListener { onBackPressed() }
    }

    //@SuppressLint("WrongViewCast")
    private fun getAndShowName() {
        val bundle = intent.extras
        val name = bundle?.get("INTENT_NAME")
        //var etResult: EditText = findViewById(R.id.txtResultado)
        //val cont = name.toString()
        txtResultado.text = "$name"

    }
}