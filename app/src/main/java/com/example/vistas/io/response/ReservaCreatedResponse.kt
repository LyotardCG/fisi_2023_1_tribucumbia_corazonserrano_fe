package com.example.vistas.io.response

import com.google.gson.annotations.SerializedName

data class ReservaCreatedResponse(
    @SerializedName("mensaje") var mensaje : String,
    @SerializedName("idReservacion") var idReservacion: Int
)
