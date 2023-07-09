package com.example.vistas.model

import com.google.gson.annotations.SerializedName

/*
*         {
            "id_reservacion": 18,
            "sillas": 4,
            "atendido": 1,
            "sede": "Chacarilla",
            "ubicacion": "Av. Primavera 545 Chacarill, Lima - Perú",
            "fecha": "2022-12-02T22:00:00.000Z"
        },
* */
data class Reservas(
    @SerializedName("idReservacion") val idReservacion: Int,
    @SerializedName("usuario") val usuario: String,
    @SerializedName("numero_sillas") val numeroSillas: Int,
    @SerializedName("atendido") val atendido: Int?,
    @SerializedName("sede") val sede: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("horario") val horario: String,
    @SerializedName("numero_mesa") val numeroMesa: Int,
    @SerializedName("QR") val qr: String?
)

