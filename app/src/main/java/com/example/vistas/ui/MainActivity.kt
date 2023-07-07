
/*
package com.example.scannerqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.scannerqr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener { initScanner() }
    }

    private fun initScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan QR Code")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                try {
                    val intent = Intent(this, Resultado2QRActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                try {
                    val intent = Intent(this, ResultadoQRActivity::class.java)
                    intent.putExtra("INTENT_NAME", result.contents)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}*/


package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vistas.R

//Importar apis
import com.example.vistas.io.ApiService
import com.example.vistas.io.UsuariosApiService
import com.example.vistas.io.CartasApiService
import com.example.vistas.io.ReservacionesApiService

import com.example.vistas.io.response.LoginResponse
import com.example.vistas.model.userDTO
import com.example.vistas.util.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var prefs: Prefs
    }
/*
    private val apiService : ApiService by lazy{
        ApiService.create()
    }*/

    private val apiService : UsuariosApiService by lazy{
        UsuariosApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_inicio)
        prefs = Prefs(applicationContext)


        val botonLogin: Button = findViewById(R.id.btnIng)
        val botonReg: Button = findViewById(R.id.btnReg)

        botonLogin.setOnClickListener {
            performLogin()
        }

        botonReg.setOnClickListener {
            gotoRegistro()
        }
    }


    private fun performLogin(){
        val user_text = findViewById<EditText>(R.id.edtUser).text.toString()
        val pass_text = findViewById<EditText>(R.id.editPass).text.toString()



        if(user_text.isNotEmpty() && pass_text.isNotEmpty() ){
            val cliente = userDTO(user_text,pass_text)

            //val call = apiService.postLogin(cliente)
            val call = apiService.login(cliente)

            call.enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {



                    if (response.isSuccessful){

                        val loginResponse = response.body()

                        if(loginResponse == null){
                            Toast.makeText(applicationContext,"se produjo un error en el servidor",Toast.LENGTH_LONG).show()
                            return
                        }

                        if(loginResponse.datos.data.idUsuario != null){
                            Toast.makeText(applicationContext,"sesion iniciada correctamente",Toast.LENGTH_LONG).show()
                            println(loginResponse)
                            prefs.saveId(loginResponse.datos.data.idUsuario)
                            prefs.saveRol(loginResponse.datos.data.rol)
                            //prefs.saveUser(loginResponse.datos.usuario)
                            prefs.saveToken(loginResponse.datos.tokenSession)

                            val intent = Intent(applicationContext, Inicio::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(applicationContext,loginResponse.mensaje,Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    //manejo del error
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "Error en la llamada al servicio web: ${t.message}")
                    // Manejo adicional del error si es necesario
                }
            })
        }else{
            showErrorName()

        }
    }

    private fun showErrorName(){
        Toast.makeText(this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show()
    }

    private fun gotoRegistro(){
        val intentRegistro = Intent(applicationContext, Registro::class.java)
        startActivity(intentRegistro)
    }
}