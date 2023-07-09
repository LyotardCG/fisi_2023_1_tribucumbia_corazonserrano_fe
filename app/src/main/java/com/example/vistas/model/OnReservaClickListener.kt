package com.example.vistas.model

interface OnReservaClickListener {
    fun onClickDetallar(codigo: Int, fecha: String, sede: String, sillas: Int, qr: String?)
    fun onClickAgregar(idReserva: Int)
}
