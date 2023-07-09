package com.example.vistas.model

import com.google.gson.annotations.SerializedName

data class ReservacionCliente(
    @SerializedName("idReservacion") val idReservacion: Int,
    @SerializedName("numero_mesa") val numeroMesa: Int,
    @SerializedName("numero_sillas") val numeroSillas: Int,
    @SerializedName("horario") val horario: String,
    @SerializedName("atendido") val atendido: Int?,
    @SerializedName("QR") val qr: String?,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("direccion") val direccion: String
)
