package com.example.vistas.io

import com.example.vistas.io.response.*
import com.example.vistas.model.ReservacionModel
import com.example.vistas.model.UserModel
import com.example.vistas.model.userDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    @POST( value = "clientes/login")
    fun postLogin(@Body userDTO : userDTO):
            Call<LoginResponse>

    @POST( value = "clientes/crear")
    fun postCliente(@Body userModel: UserModel ):
            Call<MensajeResponse>

    @Headers("Content-Type: application/json")
    @POST( value = "reservaciones/crear")
    fun postReservacion(
        @Query( value = "accion") accion : Int,
        @Body reservaModel: ReservacionModel ,
        @Header("Authorization") token: String?
    ): Call<ReservaCreatedResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "platillos")
    fun getPlatillos(
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<PlatillosResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "sedes")
    fun getSedes(
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<SedesResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "reservaciones/cliente/{id}")
    fun getReservasCliente(
        @Path( value = "id") id : Int,
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<ReservacionesResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "reservaciones/detallar/{id}")
    fun getDetalleReserva(
        @Path( value = "id") id : Int,
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<PlatilloDetalleResponse>


    companion object Factory{
        private const val BASE_URL = "http://aks-proyecto.35484a235db345ef9c3f.eastus.aksapp.io/api/v1/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

