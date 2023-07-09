package com.example.vistas.model

import com.google.gson.annotations.SerializedName

/*
*    "id_sede": 1,
     "sede": "Chacarilla",
     "ubicacion": "Av. Primavera 545 Chacarill, Lima - Perú",
     "foto": "data:image/jpeg;base64,/9j/
* */

data class Sede(
    @SerializedName("idSede") val idSede: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("direccion") val direccion: String
   // val foto : String
)
