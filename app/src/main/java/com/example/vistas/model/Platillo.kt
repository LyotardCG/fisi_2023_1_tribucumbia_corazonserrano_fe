package com.example.vistas.model

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
    val id_platillo : Int,
    val nombre : String,
    val descripcion : String,
    val precio : Float,
    val foto : String,
    val id_categoria : Int,
    val categoria : String
)