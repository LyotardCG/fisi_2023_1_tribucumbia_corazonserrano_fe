package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.io.response.ReservasClientesResponse
import com.example.vistas.model.OnReservaClickListener
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.model.ReservasAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialReserva : AppCompatActivity(),OnReservaClickListener {

    /*
        private val apiService : ApiService by lazy{
            ApiService.create()
        }
    */
    private val reservacionesApiService : ReservacionesApiService by lazy{
        ReservacionesApiService.create()
    }

    private val claseGlabal = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_historial_reservas)
        cargarHistorial()
    }

    fun cargarHistorial(){
        val clienteId : Int = MainActivity.prefs.getId()
        val call = reservacionesApiService.listarReservacionesPorCliente(clienteId)
        call.enqueue(object: Callback<ReservasClientesResponse> {
            override fun onResponse(
                call: Call<ReservasClientesResponse>,
                response: Response<ReservasClientesResponse>
            ) {
                println(response)

                if (response.isSuccessful){

                    val recyclerView = findViewById<RecyclerView>(R.id.listReservas)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                        val data = datos.reservas
                        recyclerView.adapter = ReservasAdapter(data,claseGlabal)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<ReservasClientesResponse>, t: Throwable) {
                println("error en la peticion de platillos")
                Log.e("TAG", "Error: ${t.message}")
                Toast.makeText(applicationContext, "Hola", Toast.LENGTH_SHORT).show()
            }

        } )
    }

    override fun onClickDetallar(codigo: Int, fecha: String, sede: String, sillas: Int, qr: String?) {
        val intentDetalle = Intent(this, DetalleReserva::class.java)

        intentDetalle.putExtra("codigo", codigo)
        intentDetalle.putExtra("fecha", fecha)
        intentDetalle.putExtra("sede", sede)
        intentDetalle.putExtra("sillas", sillas)
        intentDetalle.putExtra("qr", qr)
        startActivity(intentDetalle)
    }

    override fun onClickAgregar(codigo: Int) {
        val intentAgregar = Intent(this,Menu::class.java)
        intentAgregar.putExtra("id_reservacion",codigo)
        startActivity(intentAgregar)
    }
}