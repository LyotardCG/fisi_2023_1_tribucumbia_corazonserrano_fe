package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.scannerqr.Resultado2QRActivity
import com.example.vistas.R
import com.example.vistas.ui.MainActivity.Companion.prefs

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val rol = prefs.getRol()
        if(rol=="cliente"){
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fr_menu_principal_clientes)
            cargarNombreCliente()

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

        }else if (rol=="empleado"){
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fr_menu_principal_empleados)
            cargarNombreEmpleado()

            val buttonVerificar : Button = findViewById(R.id.btnScanner)

            buttonVerificar.setOnClickListener {
                val intent = Intent(applicationContext, Resultado2QRActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun cargarNombreCliente(){
        val userName = findViewById<TextView>(R.id.textViewUsuarioCliente)
        val nombre = prefs.getName()
        userName.setText(nombre)
    }

    fun cargarNombreEmpleado(){
        val userName = findViewById<TextView>(R.id.textViewUsuarioEmpleado)
        val nombre = prefs.getName()
        userName.setText(nombre)
    }



}