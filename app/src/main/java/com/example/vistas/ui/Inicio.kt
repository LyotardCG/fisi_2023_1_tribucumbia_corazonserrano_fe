package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vistas.R
import com.example.vistas.ui.MainActivity.Companion.prefs

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_menu_principal)
        cargarNombre()

        val buttonReserva : Button = findViewById(R.id.buttonResMesa)
        val buttonLista : Button = findViewById(R.id.buttonResMesa2)

        buttonReserva.setOnClickListener {
            val intent = Intent(applicationContext, ReservarSede::class.java)
            startActivity(intent)
        }
        buttonLista.setOnClickListener {
            val intent = Intent(applicationContext, HistorialReserva::class.java)
            startActivity(intent)
        }
    }

    fun cargarNombre(){
        val userName = findViewById<TextView>(R.id.TextViewUsuarioResPlatillo)
        val nombre = prefs.getName()
        userName.setText(nombre)
    }

}