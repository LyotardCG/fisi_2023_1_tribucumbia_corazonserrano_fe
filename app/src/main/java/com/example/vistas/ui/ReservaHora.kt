package com.example.vistas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vistas.R
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.ReservaCreatedResponse
import com.example.vistas.model.ReservacionModel
import com.example.vistas.ui.MainActivity.Companion.prefs
import com.example.vistas.util.Global
import com.example.vistas.util.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class ReservaHora : AppCompatActivity() {

    private var horaReserva : String = "16:00:00"
    private var cantidad : Int = 1

    private val reservacionesApiService : ReservacionesApiService by lazy{
        ReservacionesApiService.create()
    }

    companion object{
        lateinit var prefs: Prefs
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = Prefs(applicationContext)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.fr_reserva_mesa_hora)
        val btn4 = findViewById<Button>(R.id.buttonHora1)
        val btn5 = findViewById<Button>(R.id.buttonHora2)
        val btn6 = findViewById<Button>(R.id.buttonHora3)
        val btn7 = findViewById<Button>(R.id.buttonHora4)
        val btn8 = findViewById<Button>(R.id.buttonHora5)
        val btn9 = findViewById<Button>(R.id.buttonHora6)

        val buttonResMesaHora = findViewById<Button>(R.id.buttonResMesaHora)

        val btnMenos = findViewById<ImageView>(R.id.imageView5)
        val btnMas = findViewById<ImageView>(R.id.imageView4)
        val tvCantidad = findViewById<TextView>(R.id.textView2)


        btn4.setOnClickListener { this.horaReserva = "16:00:00" }
        btn5.setOnClickListener { this.horaReserva = "17:00:00" }
        btn6.setOnClickListener { this.horaReserva = "18:00:00" }
        btn7.setOnClickListener { this.horaReserva = "19:00:00" }
        btn8.setOnClickListener { this.horaReserva = "20:00:00" }
        btn9.setOnClickListener { this.horaReserva = "21:00:00" }

        buttonResMesaHora.setOnClickListener {
            guardarReserva()
        }

        btnMenos.setOnClickListener {
            if(this.cantidad > 1){
                this.cantidad = this.cantidad - 1
                tvCantidad.text = this.cantidad.toString()
            }else{
                Toast.makeText(applicationContext,"no se puede reducir la cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        btnMas.setOnClickListener {
            if(this.cantidad < 8){
                this.cantidad = this.cantidad + 1
                tvCantidad.text = this.cantidad.toString()
            }else{
                Toast.makeText(applicationContext,"el limite es 8 personas por mesa", Toast.LENGTH_SHORT).show()
            }
        }

        val nombreUsuario = prefs.getName()
        val textViewHola6 = findViewById<TextView>(R.id.hola6)
        textViewHola6.text = nombreUsuario

    }

    fun guardarReserva(){
        val sillas : Int = this.cantidad
        val id_cliente : Int = prefs.getId()
        val id_sede : Int = intent.getIntExtra("id_sede",0)
        val fecha :String = "${intent.getStringExtra("fecha")} ${this.horaReserva}"
        val numeroMesa = Random.nextInt(1, 16)


        val reserva_registro = ReservacionModel(id_cliente.toString(),id_sede.toString(),numeroMesa.toString(),sillas.toString(),fecha)

        println(reserva_registro)


        val call = reservacionesApiService.crearReservacion(
            reserva_registro
        )

        println(prefs.getToken())

        call.enqueue(object: Callback<ReservaCreatedResponse> {
            override fun onResponse(
                call: Call<ReservaCreatedResponse>,
                response: Response<ReservaCreatedResponse>
            ) {
                println(response)

                if (response.isSuccessful){

                    prefs.saveQR(response.body()!!.qr)


                    val responseReservacion = response.body()

                    if( responseReservacion == null){
                        Toast.makeText(applicationContext,"se produjo un error en el servidor",
                            Toast.LENGTH_LONG).show()
                        return
                    }else{
                        //Toast.makeText(applicationContext, responseReservacion.mensaje,Toast.LENGTH_LONG).show()
                        Toast.makeText(applicationContext, "Horario confirmado",
                            Toast.LENGTH_LONG).show()
                        val intentCheck = Intent(applicationContext, CheckMesa::class.java)
                        val nombre_sede = intent.getStringExtra("sede")
                        Global.id_reservacion_actual = responseReservacion.id
                        println("obtenido del servidor : "+  Global.id_reservacion_actual)
                        intentCheck.putExtra("sede",nombre_sede)
                        intentCheck.putExtra("cantidad",sillas)
                        intentCheck.putExtra("fecha",fecha)
                        intentCheck.putExtra("id_reservacion",responseReservacion.id)

                        startActivity(intentCheck)
                    }
                }
            }

            override fun onFailure(call: Call<ReservaCreatedResponse>, t: Throwable) {

                Toast.makeText(applicationContext,"se produjo un error en el guardado de la reservacion",
                    Toast.LENGTH_LONG).show()
                return
            }

        })
    }


}