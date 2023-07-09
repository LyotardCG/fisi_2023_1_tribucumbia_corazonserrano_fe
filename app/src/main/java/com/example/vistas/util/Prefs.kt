package com.example.vistas.util

import android.content.Context
import com.example.vistas.model.PlatilloFinal

class Prefs(val context: Context) {

    val SHARED_NAME = "datosUser"
    val SHARED_ID_USER = "id_usuario"
    val SHARED_USUARIO = "usuario"
    val SHARED_TOKEN = "token"
    val SHARED_NOMBRE = "nombre"
    val SHARED_ROL = "rol"
    val SHARED_QR = "qr"


    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun saveId(id : Int){
        storage.edit().putInt(SHARED_ID_USER,id).apply()
    }

    fun saveName(name : String){
        storage.edit().putString(SHARED_NOMBRE,name).apply()
    }

    fun saveUser(user : String){
        storage.edit().putString(SHARED_USUARIO,user).apply()
    }

    fun saveToken(token : String){
        storage.edit().putString(SHARED_TOKEN,token).apply()
    }

    fun saveRol(rol : String){
        storage.edit().putString(SHARED_ROL,rol).apply()
    }

    fun saveQR(qr : String){
        storage.edit().putString(SHARED_QR,qr).apply()
    }



    fun getId():Int{
        return storage.getInt(SHARED_ID_USER,0)
    }

    fun getName():String{
        return storage.getString(SHARED_NOMBRE,"")!!
    }

    fun getToken():String{
        return storage.getString(SHARED_TOKEN,"")!!
    }

    fun getUser():String{
        return storage.getString(SHARED_USUARIO,"")!!
    }

    fun getRol():String{
        return storage.getString(SHARED_ROL,"")!!
    }
    fun getQR():String{
        return storage.getString(SHARED_QR,"")!!
    }

}