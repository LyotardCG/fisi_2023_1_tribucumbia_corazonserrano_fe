package com.example.vistas.io.response

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("idUsuario") val idUsuario: Int,
    @SerializedName("nombres") val nombres: String,
    @SerializedName("apellidos") val apellidos: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("rol") val rol: String
)
