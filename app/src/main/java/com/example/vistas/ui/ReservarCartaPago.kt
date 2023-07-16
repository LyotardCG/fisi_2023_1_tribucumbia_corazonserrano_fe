package com.example.vistas.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.vistas.R
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.OrderRequest
import com.paypal.checkout.order.PurchaseUnit

import com.example.vistas.databinding.FrReservaCartaPagoBinding
import com.example.vistas.io.ReservacionesApiService
import com.example.vistas.io.response.MensajeResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservarCartaPago : ComponentActivity() {
    lateinit var binding: FrReservaCartaPagoBinding
    val TAG = "MyTag"
    private val apiService: ReservacionesApiService by lazy {
        ReservacionesApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrReservaCartaPagoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el total de la actividad anterior
        val total = intent.getStringExtra("total")?.toFloatOrNull() ?: 0f
        val totalConDescuento = total * 0.3f // Aplicar descuento del 30%

        // Formatear el total con dos dígitos decimales y eliminar los ceros finales
        val totalFormateado = String.format("%.2f", totalConDescuento)

        // Asignar el total formateado al TextView en la actividad actual
        val textViewTotal = findViewById<TextView>(R.id.textViewTotal)
        textViewTotal.text = totalFormateado
        val factorConversion = 0.28f // Factor de conversión de soles a dólares
        val totalEnDolares = totalFormateado.replace(",", "").toFloat() * factorConversion
        val nuevo = String.format("%.2f",totalEnDolares)

        // Configurar el botón de PayPal
        binding.paymentButtonContainer.setup(
            createOrder =
            CreateOrder { createOrderActions ->
                val order =
                    OrderRequest(
                        intent = OrderIntent.CAPTURE,
                        appContext = AppContext(userAction = UserAction.PAY_NOW),
                        purchaseUnitList =
                        listOf(
                            PurchaseUnit(
                                amount =
                                Amount(currencyCode = CurrencyCode.USD, value =nuevo)
                            )
                        )
                    )
                createOrderActions.create(order)
            },
            onApprove =
            OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.d(TAG, "CaptureOrderResult: $captureOrderResult")
                    actualizarEstado()

                }
            },
            onCancel = OnCancel {
                Log.d(TAG, "Buyer Cancelled This Purchase")
                Toast.makeText(this, "Pago cancelado", Toast.LENGTH_SHORT).show()
            },
            onError = OnError { errorInfo ->
                Log.d(TAG, "Error: $errorInfo")
                Toast.makeText(this, "Error en el pago", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun goToCheck() {
        val intentCheck = Intent(this, CheckCarta::class.java)
        startActivity(intentCheck)
    }

    private fun actualizarEstado() {
        val idReservacion = intent.getIntExtra("id_reservacion", 0)
        val call = apiService.atenderReservacion(idReservacion)
        call.enqueue(object : Callback<MensajeResponse> {
            override fun onResponse(
                call: Call<MensajeResponse>,
                response: Response<MensajeResponse>
            ) {
                if (response.isSuccessful) {
                    goToCheck()
                }
            }

            override fun onFailure(call: Call<MensajeResponse>, t: Throwable) {
                Log.d("Error", "Error en la petición: ${t.message}")
            }
        })
    }
}
