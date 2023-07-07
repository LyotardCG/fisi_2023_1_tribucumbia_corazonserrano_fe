package com.example.vistas.model
/*
*     "id_cliente": 5,
        "usuario": "gguillen",
        "nombre": "Gabriel Guillen",
        "pass": "6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090",
        "token": "92669680916c438280ee482146d8b5fb980579d7bc744d565b22d9568392bf89"
* */
/*
data class User(
    val id_cliente: Int,
    val usuario : String,
    val nombre : String,
    val token : String
)
*/
data class Usuario(
    val idUsuario: Int,
    val password: String,
    val rol: String
)


/*data class ResponseData(
    val data: Usuario,
    val tokenSession: String
)

 */

data class ResponseData(
    val idUsuario: Int,
    val password: String,
    val rol: String
)
