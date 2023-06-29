package com.example.vistas.io.response

import com.example.vistas.model.Sede
import com.google.gson.annotations.SerializedName

data class SedesResponse(
    @SerializedName("mensaje") var mensaje : String,
    @SerializedName("datos") var sedes : List<Sede>
)
