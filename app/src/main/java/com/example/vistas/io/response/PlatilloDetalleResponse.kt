package com.example.vistas.io.response

import com.example.vistas.model.PlatilloDetalle
import com.google.gson.annotations.SerializedName

data class PlatilloDetalleResponse(
    @SerializedName("datos") var platillos : List<PlatilloDetalle>
)
