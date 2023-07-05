package com.example.vistas.model
/*
* "id_platillo": 60,
        "nombre": "Limonada",
        "precio": 7,
        "foto": "data:image/jpeg;base64,/9j/
         "cantidad": 3
* */
data class PlatilloDetalle(
    val id_platillo : Int,
    val nombre : String,
    val precio : Float,
    val foto : String,
    val cantidad : Int
)
