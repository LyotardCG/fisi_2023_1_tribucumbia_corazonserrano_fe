/*  Funcionalidad de vista activity_main (fr_menu_principal_empleados)    */

package com.example.vistas.ui
/*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vistas.databinding.FrMenuPrincipalEmpleadosBinding
import com.example.vistas.ui.Resultado2QRActivity
import com.example.vistas.ui.ResultadoQRActivity
import com.google.zxing.integration.android.IntentIntegrator

class ScannerQR : AppCompatActivity() {

    private lateinit var binding: FrMenuPrincipalEmpleadosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrMenuPrincipalEmpleadosBinding.inflate(layoutInflater)
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
}
*/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.ReservasDetallesResponse
import com.example.vistas.ui.Resultado2QRActivity
import com.example.vistas.ui.ResultadoQRActivity
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScannerQR : AppCompatActivity() {

    private val reservacionesApiService: ReservacionesApiService by lazy {
        ReservacionesApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initScanner()
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan QR Code")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                val intent = Intent(this, Resultado2QRActivity::class.java)
                startActivity(intent)
            } else {
                val reservaId = result.contents.toIntOrNull()
                if (reservaId != null) {
                    verificarReservacion(reservaId)
                } else {
                    val intent = Intent(this, Resultado2QRActivity::class.java)
                    startActivity(intent)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        finish()
    }

    private fun verificarReservacion(reservaId: Int) {
        val call = reservacionesApiService.verificarReservacion(reservaId)
        call.enqueue(object : Callback<ReservasDetallesResponse> {
            override fun onResponse(
                call: Call<ReservasDetallesResponse>,
                response: Response<ReservasDetallesResponse>
            ) {
                if (response.isSuccessful) {
                    val detallesReserva = response.body()?.reserva
                    detallesReserva?.let {
                        val sharedPreferences = getSharedPreferences("ReservaData", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putInt("reservaId", it.idReservacion)
                        editor.putString("usuario", it.usuario)
                        editor.putInt("numeroSillas", it.numeroSillas)
                        editor.putString("sede", it.sede)
                        editor.putString("horario", it.horario)
                        editor.apply()

                        val intent = Intent(this@ScannerQR, ResultadoQRActivity::class.java)
                        startActivity(intent)
                        finish()
                    } ?: run {
                        val intent = Intent(this@ScannerQR, Resultado2QRActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    val intent = Intent(this@ScannerQR, Resultado2QRActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<ReservasDetallesResponse>, t: Throwable) {
                val intent = Intent(this@ScannerQR, Resultado2QRActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}







