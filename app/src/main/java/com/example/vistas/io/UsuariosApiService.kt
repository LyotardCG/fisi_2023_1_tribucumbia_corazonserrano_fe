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

interface UsuariosApiService {
    //No es necesario ahora
    @Headers("Content-Type: application/json")
    @GET("listar-usuarios")
    fun listarUsuarios(): Call<Unit>

    //Es necesario
    @GET("detallar-usuarios/{userId}")
    fun getDetalleUsuario(@Path("userId") userId: Int): Call<UsuarioResponse>
    /*
     //No son necesarios
        @GET("listar-usuarios-empleados")
        fun listarUsuariosEmpleados(): Call<Unit>

        @GET("detallar-usuarios-empleados/{employeeId}")
        fun getDetalleUsuarioEmpleado(@Path("employeeId") employeeId: Int): Call<Unit>

        @PUT("actualizar-usuarios-empleados/{employeeId}")
        fun actualizarUsuarioEmpleado(
            @Path("employeeId") employeeId: Int,
            @Body empleado: Empleado
        ): Call<Unit>

        @DELETE("eliminar-usuarios-empleados/{employeeId}")
        fun eliminarUsuarioEmpleado(@Path("employeeId") employeeId: Int): Call<Unit>

        @GET("listar-usuarios-clientes")
        fun listarUsuariosClientes(): Call<Unit>

        @GET("detallar-usuarios-clientes/{clientId}")
        fun getDetalleUsuarioCliente(@Path("clientId") clientId: Int): Call<Unit>

        @PUT("actualizar-usuarios-clientes/{clientId}")
        fun actualizarUsuarioCliente(
            @Path("clientId") clientId: Int,
            @Body cliente: Cliente
        ): Call<Unit>

        @DELETE("eliminar-usuarios-clientes/{clientId}")
        fun eliminarUsuarioCliente(@Path("clientId") clientId: Int): Call<Unit>
    */

    @POST("login")
    fun login(@Body userDTO : userDTO):
            Call<LoginResponse>
    //Es necesario
    @POST("registrar-usuarios")
    fun registrarUsuario(@Body usuario: UserModel): Call<MensajeResponse>

    companion object {


        private const val BASE_URL = "http://10.0.2.2:9003/ne-usuarios/servicio-al-cliente/v1/"
        //private const val BASE_URL = "http://api-usuarios.eb36bc0f9bda49e495fa.eastus.aksapp.io/ne-usuarios/servicio-al-cliente/v1/"
        //private const val BASE_URL = "https://apim-sanjoylao-prod-001.azure-api.net/api-usuarios/ux-usuarios/sjl/servicio-al-cliente/v1/"

        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun create(): UsuariosApiService {
            return retrofit.create(UsuariosApiService::class.java)
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