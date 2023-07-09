package com.example.vistas.io.response

import com.example.vistas.model.ReservaCartaDetalle
import com.google.gson.annotations.SerializedName

data class ReservasCartasDetallesResponse(
    @SerializedName("reserva") val reserva: List<ReservaCartaDetalle>
)
