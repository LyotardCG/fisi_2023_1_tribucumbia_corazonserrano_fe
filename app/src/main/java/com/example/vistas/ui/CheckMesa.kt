package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.vistas.R
import com.example.vistas.ui.MainActivity.Companion.prefs

class CheckMesa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_mesa_confirmada)

        val buttonMesaConfInicio = findViewById<Button>(R.id.buttonMesaConfInicio)

        cargarInfo()

        buttonMesaConfInicio.setOnClickListener {
            gotoInicio()
        }

    }

    fun cargarInfo(){
        val usuario = findViewById<TextView>(R.id.textViewUsuarioMesaConfir)
        val sede = findViewById<TextView>(R.id.textView5)
        val sillas = findViewById<TextView>(R.id.textView10)
        val fecha = findViewById<TextView>(R.id.textView13)

        usuario.text = prefs.getName()
        sede.text = "Sede : ${intent.getStringExtra("sede")}"
        sillas.text = "Sillas : ${intent.getIntExtra("cantidad",0)}"
        fecha.text = "Fecha : ${intent.getStringExtra("fecha")}"
    }

    fun gotoInicio(){
        val intentInicio = Intent(this,Inicio::class.java)

        startActivity(intentInicio)
    }
}