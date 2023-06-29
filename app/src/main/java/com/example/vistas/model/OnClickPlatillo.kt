package com.example.vistas.model

interface OnClickPlatillo {
    fun onClick(
        idPlatillo: Int,
        nombre: String,
        descripcion:String,
        foto:String,
        precio:Float
    )
}