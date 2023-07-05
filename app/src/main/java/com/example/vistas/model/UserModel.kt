package com.example.vistas.model

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

data class UserModel(
    val usuario : String,
    val pass : String,
    val nombre : String,
    val telefono : String,
    val dni : String,
    val correo : String
)
