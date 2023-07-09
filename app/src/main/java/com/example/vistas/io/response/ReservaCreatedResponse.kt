package com.example.vistas.io.response

import com.google.gson.annotations.SerializedName

data class ReservaCreatedResponse(
    @SerializedName("mensaje") val mensaje: String,
    @SerializedName("id") val id: Int,
    @SerializedName("qr") val qr: String
)