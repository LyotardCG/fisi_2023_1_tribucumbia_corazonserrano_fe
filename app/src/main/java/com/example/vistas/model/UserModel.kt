package com.example.vistas.model

import com.google.gson.annotations.SerializedName

/*
* {
    "usuario": "jreynoso",
    "pass" : "abc123",
    "nombre": "Jhonas Reynoso",
    "telefono": "926636364",
    "dni" : "76836277",
    "correo": "example@gmail.com"
}
* */

/*
{
    "idRol": "2",
    "idSede": "2",
    "nombres":"Pedro",
    "apellidos":"Gutierrez",
    "telefono":"9993933",
    "correo":"pedross@pedro.com",
    "password":"123"
}
 */

data class UserModel(
    /*
    val usuario : String,
    val pass : String,
    val nombre : String,
    val telefono : String,
    val dni : String,
    val correo : String*/
    val correo: String,
    val password: String,
    val nombres: String,
    val apellidos: String,
    val telefono: String,
    val idRol: Int,
    val dni: String
)
