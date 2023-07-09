package com.example.vistas.io.response

//import com.example.vistas.model.User
import com.example.vistas.model.ResponseData

/*
data class LoginResponse(
    val mensaje : String,
    val datos : ResponseData
)
*/

data class LoginResponse(
    val data : ResponseData,
    val tokenSession:String
)