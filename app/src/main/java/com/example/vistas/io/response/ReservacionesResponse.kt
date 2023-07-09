package com.example.vistas.io.response

import com.example.vistas.model.Reservas
import com.google.gson.annotations.SerializedName

data class ReservacionesResponse(
    @SerializedName("mensaje") var mensaje : String,
    @SerializedName("datos") var reservas : List<Reservas>
)
