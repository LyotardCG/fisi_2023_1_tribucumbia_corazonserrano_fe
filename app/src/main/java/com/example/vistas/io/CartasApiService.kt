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


interface CartasApiService {
    //Es necesario
    @Headers("Content-Type: application/json")
    @GET("listar-platillos")
    fun getPlatillos(): Call<PlatillosResponse>

    //Es necesario
    @Headers("Content-Type: application/json")
    @GET("detallar-platillos/{id}")
    fun getDetallePlatillo(@Path("id") id: Int): Call<PlatillosResponse>

    //No se va a utilizar
    @Headers("Content-Type: application/json")
    @GET("listar-categorias")
    fun getCategorias(): Call<CategoriasResponse>

    //Si se utiliza
    @Headers("Content-Type: application/json")
    @GET("detallar-reservas-cartas/{id}")
    fun getDetalleReservaCarta(@Path("id") id: Int): Call<ReservasCartasDetallesResponse>

    //Si se utiliza
    @POST("reservar-cartas")
    fun reservarCarta(@Body reserva: ReservaCartaModel): Call<MensajeResponse>

    companion object {

        private const val BASE_URL = "http://10.0.2.2:9001/ne-cartas/servicio-al-cliente/v1/"
        //private const val BASE_URL = "http://api-cartas.2db5f954f43042069dae.eastus.aksapp.io/ne-cartas/servicio-al-cliente/v1/"
        //private const val BASE_URL = "https://apim-sanjoylao-prod-001.azure-api.net/api-cartas/ux-cartas/sjl/servicio-al-cliente/v1/"


        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun create(): CartasApiService {
            return retrofit.create(CartasApiService::class.java)
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