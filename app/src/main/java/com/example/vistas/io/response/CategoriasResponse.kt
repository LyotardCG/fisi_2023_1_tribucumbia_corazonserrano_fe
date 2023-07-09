package com.example.vistas.io.response

import com.example.vistas.model.Categoria
import com.google.gson.annotations.SerializedName

data class CategoriasResponse(
    @SerializedName("categorias") val categorias: List<Categoria>
)
