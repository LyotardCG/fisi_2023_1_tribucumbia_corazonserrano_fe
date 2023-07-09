package com.example.vistas.model

import com.google.gson.annotations.SerializedName

/*
*       "id_platillo": 11,
        "nombre": "chancho al palo",
        "descripcion": "Prueba nuestra nueva estacion de chancho",
        "precio": 18,
        "foto": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAGQAZAAD/
        "id_categoria": 1,
        "categoria": "carnes"
* */

data class Platillo(
    @SerializedName("idPlatillo") val idPlatillo: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("precio") val precio: Double,
    @SerializedName("foto") val foto: String,
    @SerializedName("idCategoria") val idCategoria: Int,
    @SerializedName("categoria") val categoria: String
)