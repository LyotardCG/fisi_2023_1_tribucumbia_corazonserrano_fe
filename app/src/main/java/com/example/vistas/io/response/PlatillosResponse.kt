package com.example.vistas.io.response

import com.example.vistas.model.Platillo
import com.google.gson.annotations.SerializedName

data class PlatillosResponse(
    @SerializedName("mensaje") var mensaje : String,
    @SerializedName("datos") var platillos : List<Platillo>
)
