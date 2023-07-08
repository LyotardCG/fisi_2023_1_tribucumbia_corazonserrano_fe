/* Funcionalidad de vista fr_reserva_encontrada */
package com.example.escaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultQRActivity : AppCompatActivity() {
    lateinit var txtResultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_encontrada)
        txtResultado = findViewById(R.id.txtResultado)
        val btnVolver1: Button = findViewById(R.id.btnVolver)
        getAndShowName()
        btnVolver1.setOnClickListener { onBackPressed() }
    }
    private fun getAndShowName() {
        val bundle = intent.extras
        val name = bundle?.get("INTENT_NAME")
        //var etResult: EditText = findViewById(R.id.txtResultado)
        //val cont = name.toString()
        txtResultado.text = "$name"

    }
}