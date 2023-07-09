package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.MensajeResponse
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.ui.MainActivity.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservarCartaPago : AppCompatActivity() {
    private val ApiService : ReservacionesApiService by lazy{
        ReservacionesApiService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_carta_pago)
        val btnPagarPaypal = findViewById<Button>(R.id.buttonPaypal)

        // Obtener el total de la actividad anterior
        val total = intent.getStringExtra("total")?.toFloatOrNull() ?: 0f
        val totalConDescuento = total * 0.3f // Aplicar descuento del 30%

        // Formatear el total con dos dígitos decimales y eliminar los ceros finales
        val totalFormateado = String.format("%.2f", totalConDescuento).toFloat()

        // Asignar el total formateado al TextView en la actividad actual
        val textViewTotal = findViewById<TextView>(R.id.textViewTotal)
        textViewTotal.text = totalFormateado.toString()

        // Resto del código de la actividad ReservarCartaPago
        btnPagarPaypal.setOnClickListener { actualizarEstado() }
    }
    fun goToCheck(){
        val intentCheck = Intent(this,CheckCarta::class.java)
        startActivity(intentCheck)
    }

    fun actualizarEstado(){

        val call =  ApiService.atenderReservacion(intent.getIntExtra("id_reservacion", 0))
        call.enqueue(object: Callback<MensajeResponse> {
            override fun onResponse(
                call: Call<MensajeResponse>,
                response: Response<MensajeResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    goToCheck()
                }
            }

            override fun onFailure(call: Call<MensajeResponse>, t: Throwable) {
                println("error en la petición")
            }
        } )
    }
}

