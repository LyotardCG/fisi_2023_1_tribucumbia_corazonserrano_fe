package com.example.vistas.io.response

import com.example.vistas.model.Reservas
import com.google.gson.annotations.SerializedName

data class ReservasDetallesResponse(
    @SerializedName("reserva") val reserva: Reservas
)
