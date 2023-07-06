package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.LoginResponse
import com.example.vistas.io.response.MensajeResponse
import com.example.vistas.model.UserModel
import com.example.vistas.ui.MainActivity.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {

    private val apiService : ApiService by lazy{
        ApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_registro_usuario)

        val buttonRegUser : Button = findViewById(R.id.buttonRegUser)

        buttonRegUser.setOnClickListener {
            regUsuario()
        }

    }

    fun regUsuario(){
        val nombres_text = findViewById<EditText>(R.id.edtUserNomUser).text.toString()
        val dni_text = findViewById<EditText>(R.id.edtUserApeUser).text.toString()
        val telefono_text = findViewById<EditText>(R.id.edtUserTelUser).text.toString()
        val correo_text = findViewById<EditText>(R.id.edtUserCorreoUser).text.toString()
        val usuario_text = findViewById<EditText>(R.id.edtUserCorreoUser).text.toString()
        // esto agregue 2023-1: val usuario_text = findViewById<EditText>(R.id.edtUserCorreoUser2).text.toString()
        val pass_text = findViewById<EditText>(R.id.editPassContUser).text.toString()

        val user_registro = UserModel(usuario_text,pass_text,nombres_text,telefono_text,dni_text,correo_text)

        val call = apiService.postCliente(user_registro)
        call.enqueue(object: Callback<MensajeResponse> {
            override fun onResponse(
                call: Call<MensajeResponse>,
                response: Response<MensajeResponse>
            ) {
                if (response.isSuccessful){
                    val mensajeResponse = response.body()

                    if(mensajeResponse == null){
                        Toast.makeText(applicationContext,"se produjo un error en el servidor",
                            Toast.LENGTH_LONG).show()
                        return
                    }else{
                        Toast.makeText(applicationContext,mensajeResponse.mensaje,
                            Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<MensajeResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"se produjo un error en la creacion del registro",
                    Toast.LENGTH_LONG).show()
                return
            }

        })

    }


}