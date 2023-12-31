package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.CartasApiService
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.model.OnClickPlatillo
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.ui.MainActivity.Companion.prefs
import com.example.vistas.util.Global
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Menu : AppCompatActivity(),OnClickPlatillo {

    private val claseGlabal = this

    private var id_reservacion : Int = 0

    private val ApiService : CartasApiService by lazy{
        CartasApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_carta)
        this.id_reservacion = intent.getIntExtra("id_reservacion",0)
        cargarPlatillos()
    }




    fun cargarPlatillos(){
        val call =  ApiService.getPlatillos()
        call.enqueue(object: Callback<PlatillosResponse>{
            override fun onResponse(
                call: Call<PlatillosResponse>,
                response: Response<PlatillosResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.listDetReserva)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                       val data = datos.platillos
                        recyclerView.adapter = PlatilloAdapter(data,claseGlabal)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<PlatillosResponse>, t: Throwable) {
                println("error en la peticion de platillos")
            }

        } )
    }

    override fun onClick(
        idPlatillo: Int,
        nombre: String,
        descripcion: String,
        foto: String,
        precio: Float
    ) {


        val intentPlatillo = Intent(this,AgregarPlatillo::class.java)

        println("desde el menu : " +   Global.id_reservacion_actual)

        intentPlatillo.putExtra("id_reservacion",  Global.id_reservacion_actual)
        intentPlatillo.putExtra("id_platillo",idPlatillo)
        intentPlatillo.putExtra("nombre",nombre)
        intentPlatillo.putExtra("descripcion",descripcion)
        intentPlatillo.putExtra("foto",foto)
        intentPlatillo.putExtra("precio",precio)
        startActivity(intentPlatillo)
    }


}