package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.SedesResponse
import com.example.vistas.model.OnSedeClickListener
import com.example.vistas.model.SedeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservarSede : AppCompatActivity(), OnSedeClickListener {

    private val apiService : ApiService by lazy{
        ApiService.create()
    }

    private val claseGlabal = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_mesa_sede)
        cargarSedes()
    }

    fun cargarSedes(){
        val call = apiService.getSedes(20,"Bearer ${MainActivity.prefs.getToken()}")
        call.enqueue(object: Callback<SedesResponse> {
            override fun onResponse(
                call: Call<SedesResponse>,
                response: Response<SedesResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.listSedes)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                        val data = datos.sedes
                        recyclerView.adapter = SedeAdapter(data,claseGlabal)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<SedesResponse>, t: Throwable) {
                println("error en la peticion de sedes")
            }

        } )
    }

    override fun onClick(idSede: Int, sede: String) {
        val intent = Intent(this,ReservaFecha::class.java)

        intent.putExtra("id_sede",idSede)
        intent.putExtra("sede",sede)
        startActivity(intent)
    }
}