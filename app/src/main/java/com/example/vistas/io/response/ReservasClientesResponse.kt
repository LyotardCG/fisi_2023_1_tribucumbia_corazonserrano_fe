package com.example.vistas.io.response

import com.example.vistas.model.ReservacionCliente
import com.google.gson.annotations.SerializedName

data class ReservasClientesResponse(
    @SerializedName("reservas") val reservas: List<ReservacionCliente>
)
