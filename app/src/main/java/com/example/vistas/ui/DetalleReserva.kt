package com.example.vistas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.PlatilloDetalleResponse
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.DetalleAdapter
import com.example.vistas.model.PlatilloAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalleReserva : AppCompatActivity() {


    private val apiService : ApiService by lazy{
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_detalle_reserva)
        cargarInformacionReserva()
        cargarDetalle()
    }

    fun cargarInformacionReserva(){
        val codigo = findViewById<TextView>(R.id.TextViewCodDetReserva)
        val fecha = findViewById<TextView>(R.id.TextViewDataHoraReserva)
        val sede = findViewById<TextView>(R.id.textViewDataFechaReserva)
        val sillas = findViewById<TextView>(R.id.textViewSillas)

        codigo.text = "#${intent.getIntExtra("codigo",0)}"
        fecha.text = intent.getStringExtra("fecha")
        sede.text = intent.getStringExtra("sede")
        sillas.text = intent.getIntExtra("sillas",0).toString()


    }

    fun cargarDetalle(){
        val call = apiService.getDetalleReserva (intent.getIntExtra("codigo",0),29,"Bearer ${MainActivity.prefs.getToken()}")
        call.enqueue(object: Callback<PlatilloDetalleResponse> {
            override fun onResponse(
                call: Call<PlatilloDetalleResponse>,
                response: Response<PlatilloDetalleResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.listDetReserva)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                        val data = datos.platillos
                        recyclerView.adapter = DetalleAdapter(data)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<PlatilloDetalleResponse>, t: Throwable) {
                println("error en la peticion del detalle")
            }

        } )
    }

}