package com.example.vistas.ui

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.CartasApiService
//import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.DetallesAdapter
import com.example.vistas.model.PlatilloAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Base64
import com.example.vistas.io.response.ReservasCartasDetallesResponse

class DetalleReserva : AppCompatActivity() {

/*
    private val apiService : ApiService by lazy{
        ApiService.create()
    }
*/

    private val cartasApiService : CartasApiService by lazy{
        CartasApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_detalle_reserva)
        cargarInformacionReserva()
        cargarDetalle()
    }

    fun cargarInformacionReserva() {
        val codigo = findViewById<TextView>(R.id.TextViewCodDetReserva)
        val fecha = findViewById<TextView>(R.id.TextViewDataHoraReserva)
        val sede = findViewById<TextView>(R.id.textViewDataFechaReserva)
        val sillas = findViewById<TextView>(R.id.textViewSillas)
        val imagenqr=findViewById<ImageView>(R.id.imageView)

        codigo.text = "#${intent.getIntExtra("codigo", 0)}"
        fecha.text = intent.getStringExtra("fecha")
        sede.text = intent.getStringExtra("sede")
        sillas.text = intent.getIntExtra("sillas", 0).toString()


        val base64String = intent.getStringExtra("qr")
        if(base64String != null && base64String != "1") {
            val base64Image = base64String.split(",").toTypedArray()[1]

            val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

            imagenqr.setImageBitmap(decodedByte)
        }
    }

    fun cargarDetalle(){
        val call = cartasApiService.getDetalleReservaCarta (intent.getIntExtra("codigo",0))
        call.enqueue(object: Callback<ReservasCartasDetallesResponse> {
            override fun onResponse(
                call: Call<ReservasCartasDetallesResponse>,
                response: Response<ReservasCartasDetallesResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.listDetReserva)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                        val data = datos.reserva
                        recyclerView.adapter = DetallesAdapter(data)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<ReservasCartasDetallesResponse>, t: Throwable) {
                println("error en la peticion del detalle")
            }

        } )
    }

}