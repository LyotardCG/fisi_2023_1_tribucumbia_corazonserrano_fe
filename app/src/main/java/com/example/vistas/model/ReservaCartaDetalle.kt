package com.example.vistas.model

import com.google.gson.annotations.SerializedName

data class ReservaCartaDetalle(
    @SerializedName("idPlatillo") val idPlatillo: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("precio") val precio: Double,
    @SerializedName("foto") val foto: String,
    @SerializedName("cantidad") val cantidad: Int
)
