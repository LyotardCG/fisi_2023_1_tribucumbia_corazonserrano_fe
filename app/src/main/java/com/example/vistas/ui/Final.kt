package com.example.vistas.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.CartasApiService
import com.example.vistas.io.response.MensajeResponse
import com.example.vistas.model.AgregarReserva
import com.example.vistas.model.Platillo
import com.example.vistas.model.PlatilloAgregado
import com.example.vistas.model.PlatilloFinal
import com.example.vistas.model.PlatilloFinalAdapter
import com.example.vistas.model.ReservaCarta
import com.example.vistas.model.ReservaCartaModel
import com.example.vistas.util.Global
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Final : AppCompatActivity() {

    private var id_reserva: Int = 0
    private val apiService: CartasApiService by lazy {
        CartasApiService.create()
    }

    private var lista: MutableList<PlatilloFinal> = mutableListOf()
    private var reserva: ReservaCarta = ReservaCarta(lista)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_carta_adicional)
        cargarLista()

        val btnCargarOtroPlatillo = findViewById<Button>(R.id.agregarOtro)
        val btnGuardar = findViewById<Button>(R.id.guardar)

        btnCargarOtroPlatillo.setOnClickListener { elegirOtroPlatillo() }
        btnGuardar.setOnClickListener { guardarPlatillo() }
    }

    private fun cargarLista() {
        val reservacion = intent.getIntExtra("id_reservacion", 0)
        this.id_reserva = reservacion
        println("desde el final  : $reservacion")
        println("desde el final THIS  : $id_reserva")
        val tvtotal = findViewById<TextView>(R.id.textViewCalculoTotal)
        var total = 0.0f
        for (platillo in Global.platillosSeleccionados) {
            total += platillo.precio * platillo.cantidad
        }

        tvtotal.text = total.toString()

        println(reservacion)
        println(Global.platillosSeleccionados)
        val recyclerView = findViewById<RecyclerView>(R.id.listDetReserva)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val datos = Global.platillosSeleccionados
        if (datos != null) {
            recyclerView.adapter = PlatilloFinalAdapter(datos)
        }
    }

    private fun elegirOtroPlatillo() {
        val intentAgregar = Intent(applicationContext, Menu::class.java)
        intentAgregar.putExtra("id_reservacion", this.id_reserva)
        startActivity(intentAgregar)
    }

    private fun guardarPlatillo() {
        println("entrando a guardar reserva")
        val lista = ArrayList<PlatilloAgregado>()
        for (platillo in Global.platillosSeleccionados) {
            val p = PlatilloAgregado(platillo.id_platillo, platillo.cantidad)
            lista.add(p)
        }

        val listaDeAgregados = ReservaCartaModel(
            intent.getIntExtra("id_reservacion", 0),
            lista
        )

        println(listaDeAgregados)

        val call = apiService.reservarCarta(listaDeAgregados)
        call.enqueue(object : Callback<MensajeResponse> {
            override fun onResponse(
                call: Call<MensajeResponse>,
                response: Response<MensajeResponse>
            ) {
                if (response.isSuccessful) {
                    println("si hay response")
                    val mensajeResponse = response.body()

                    if (mensajeResponse == null) {
                        Toast.makeText(
                            applicationContext,
                            "se produjo un error en el servidor",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    } else {
                        println("si hay mensaje")
                        val total = findViewById<TextView>(R.id.textViewCalculoTotal).text.toString()

                        val intent = Intent(applicationContext, ReservarCartaPago::class.java)
                        intent.putExtra("total", total)
                        intent.putExtra("id_reservacion",intent.getIntExtra("id_reservacion", id_reserva))
                        startActivity(intent)
                    }
                } else {
                    println("No hay response")
                    Toast.makeText(
                        applicationContext,
                        response.message().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<MensajeResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "se produjo un error en la creacion del registro",
                    Toast.LENGTH_LONG
                ).show()
                return
            }
        })
    }
}
