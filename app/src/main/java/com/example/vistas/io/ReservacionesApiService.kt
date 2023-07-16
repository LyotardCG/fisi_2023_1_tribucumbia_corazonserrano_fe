package com.example.vistas.io

import com.example.vistas.io.response.*
import com.example.vistas.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

interface ReservacionesApiService {

    //Es necesario
    @POST("crear-reservaciones")
    fun crearReservacion(@Body reservacion: ReservacionModel): Call<ReservaCreatedResponse>

    //Este tambien es necesario

    @Headers("Content-Type: application/json")
    @GET("detallar-reservaciones/{reservacionId}")
    fun getDetalleReservacion(@Path("reservacionId") reservacionId: Int): Call<ReservasDetallesResponse>

    //Este no es necesario
    @GET("listar-reservaciones")
    fun listarReservaciones(): Call<Unit>

    //Este no es necesario
    @GET("listar-reservaciones-sedes/{sedeId}")
    fun listarReservacionesPorSede(@Path("sedeId") sedeId: Int): Call<Unit>

    //Este SI
    @Headers("Content-Type: application/json")
    @GET("listar-reservaciones-clientes/{clienteId}")
    fun listarReservacionesPorCliente(@Path("clienteId") clienteId: Int): Call<ReservasClientesResponse>

    //Este no es necesario
    @PUT("actualizar-reservaciones/{id}")
    fun actualizarReservacion(@Path("id") id: Int): Call<Unit>

    //Este no es necesario
    @DELETE("eliminar-reservaciones/{id}")
    fun eliminarReservacion(@Path("id") id: Int): Call<Unit>

    //Este si es necesario
    @GET("verificar-reservaciones/{id}")
    fun verificarReservacion(@Path("id") id: Int): Call<ReservasDetallesResponse>

    //Este si es necesario
    @PUT("atender-reservaciones/{id}")
    fun atenderReservacion(@Path("id") id: Int): Call<MensajeResponse>

    //Este si es necesario
    @Headers("Content-Type: application/json")
    @GET("listar-sedes")
    fun listarSedes(): Call<SedesResponse>

    //Este si es necesario
    @GET("detallar-sedes/{id}")
    fun getDetalleSede(@Path("id") id: Int): Call<Sede>

    companion object {

        private const val BASE_URL = "http://10.0.2.2:9002/ne-reservaciones/servicio-al-cliente/v1/"
        //private const val BASE_URL = "http://api-reservaciones.eb36bc0f9bda49e495fa.eastus.aksapp.io/ne-reservaciones/servicio-al-cliente/v1/"
        //private const val BASE_URL = "https://apim-sanjoylao-prod-001.azure-api.net/api-reservaciones/ux-reservaciones/sjl/servicio-al-cliente/v1/"


        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun create(): ReservacionesApiService {
            return retrofit.create(ReservacionesApiService::class.java)
        }

        private class HeaderInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val modifiedRequest = originalRequest.newBuilder()
                    .header("Ocp-Apim-Subscription-Key", "cfb7844ee9544a76b2a316a1b7818422")
                    .build()
                return chain.proceed(modifiedRequest)
            }
        }
    }
}

