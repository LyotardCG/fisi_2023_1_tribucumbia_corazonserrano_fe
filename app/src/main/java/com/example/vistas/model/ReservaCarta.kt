package com.example.vistas.model

class ReservaCarta(var lista : MutableList<PlatilloFinal>) {

    var listaIntera = lista

    fun addPLatillo(p : PlatilloFinal){
        listaIntera.add(p)
    }

    fun mostrar(){
        println(listaIntera)
    }

    @JvmName("getLista1")
    fun getLista(): MutableList<PlatilloFinal> {
        return listaIntera
    }

}