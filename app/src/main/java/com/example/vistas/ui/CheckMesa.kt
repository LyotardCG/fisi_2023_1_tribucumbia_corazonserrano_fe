package com.example.vistas.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.vistas.R
import com.example.vistas.ui.MainActivity.Companion.prefs
import com.example.vistas.util.Prefs
import android.util.Base64

class CheckMesa : AppCompatActivity() {

    companion object{
        lateinit var prefs: Prefs
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = Prefs(applicationContext)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_mesa_confirmada)
        val buttonMesaConfInicio = findViewById<Button>(R.id.buttonMesaConfInicio)

        cargarInfo()

        buttonMesaConfInicio.setOnClickListener {
            gotoInicio()
        }

    }

    fun cargarInfo(){
        val usuario = findViewById<TextView>(R.id.textViewUsuarioMesaConfir)
        val sede = findViewById<TextView>(R.id.textView5)
        val sillas = findViewById<TextView>(R.id.textView10)
        val fecha = findViewById<TextView>(R.id.textView13)


        val prueba=findViewById<ImageView>(R.id.imageViewImagenConfirmacion)

        usuario.text = prefs.getName()
        sede.text = "Sede : ${intent.getStringExtra("sede")}"
        sillas.text = "Sillas : ${intent.getIntExtra("cantidad",0)}"
        fecha.text = "Fecha : ${intent.getStringExtra("fecha")}"

        //Poniendo el QR

        val base64String = prefs.getQR()
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        prueba.setImageBitmap(decodedByte)


    }

    fun gotoInicio(){
        val intentInicio = Intent(this,Inicio::class.java)

        startActivity(intentInicio)
    }
}