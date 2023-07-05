package com.example.vistas.io.response

import com.example.vistas.model.User

data class LoginResponse(
    val mensaje : String,
    val datos : User
)
