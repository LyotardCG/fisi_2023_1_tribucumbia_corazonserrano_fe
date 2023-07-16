package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.vistas.R
import com.example.vistas.io.UsuariosApiService
import com.example.vistas.io.response.UsuarioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Inicio : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_SCANNER = 1
    }

    private val usuariosApiService: UsuariosApiService by lazy {
        UsuariosApiService.create()
    }

    private var nombreEmpleado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_menu_principal_clientes)

        val buttonsalir: Button = findViewById(R.id.buttonsalir)

        buttonsalir.setOnClickListener {
            logout()
        }

        obtenerDetalleUsuario()


    }

    private fun obtenerDetalleUsuario() {
        val userId = MainActivity.prefs.getId()
        val call = usuariosApiService.getDetalleUsuario(userId)
        call.enqueue(object : Callback<UsuarioResponse> {
            override fun onResponse(
                call: Call<UsuarioResponse>,
                response: Response<UsuarioResponse>
            ) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    usuario?.let {
                        val nombre = it.nombres
                        MainActivity.prefs.saveName(nombre) // Guardar el nombre en SharedPreferences
                        //Toast.makeText(applicationContext, "Nombre del usuario: $nombre", Toast.LENGTH_SHORT).show()
                        cargarNombre(nombre)

                        val rol = MainActivity.prefs.getRol()
                        if (rol == "empleado") {
                            nombreEmpleado = nombre
                            ajustarVistaEmpleado()
                        } else if (rol == "cliente") {
                            ajustarVistaCliente()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })
    }

    private fun logout(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    private fun cargarNombre(nombre: String) {
        val userName = findViewById<TextView>(R.id.textViewUsuarioCliente)
        userName.text = nombre
    }

    private fun ajustarVistaCliente() {
        val buttonReserva: Button = findViewById(R.id.buttonResMesa)
        val buttonLista: Button = findViewById(R.id.buttonResMesa2)

        buttonReserva.setOnClickListener {
            val intent = Intent(applicationContext, ReservarSede::class.java)
            startActivity(intent)
        }

        buttonLista.setOnClickListener {
            val intent = Intent(applicationContext, HistorialReserva::class.java)
            startActivity(intent)
        }
    }

    private fun ajustarVistaEmpleado() {
        setContentView(R.layout.fr_menu_principal_empleados)

        val userName = findViewById<TextView>(R.id.textViewUsuarioEmpleado)
        userName.text = nombreEmpleado

        val buttonVerificar: Button = findViewById(R.id.btnScanner)

        buttonVerificar.setOnClickListener {
            val intent = Intent(applicationContext, ScannerQR::class.java)
            startActivityForResult(intent, REQUEST_CODE_SCANNER)
        }
        val buttonsalir: Button = findViewById(R.id.buttonsalirempleado)

        buttonsalir.setOnClickListener {
            logout()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SCANNER && resultCode == RESULT_OK) {
            val result = data?.getStringExtra("INTENT_NAME")
            // Utilizar el resultado como desees
        }
    }
}
