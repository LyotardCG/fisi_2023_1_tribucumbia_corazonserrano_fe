package com.example.vistas.io

import com.example.vistas.io.response.*
import com.example.vistas.model.ReservacionModel
import com.example.vistas.model.UserModel
import com.example.vistas.model.userDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.annotations.SerializedName


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

interface CartasApiService {
    @GET("listar-platillos")
    fun getPlatillos(): Call<Unit>

    @GET("detallar-platillos/{id}")
    fun getDetallePlatillo(@Path("id") id: Int): Call<Unit>

    @GET("listar-categorias")
    fun getCategorias(): Call<Unit>

    @GET("detallar-reservas-cartas/{id}")
    fun getDetalleReservaCarta(@Path("id") id: Int): Call<Unit>

    @POST("reservar-cartas")
    fun reservarCarta(@Body reserva: ReservaCarta): Call<Unit>

    companion object {
        private const val BASE_URL = "http://localhost:9000/ne-cartas/servicio-al-cliente/v1/"

        fun create(): CartasApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CartasApiService::class.java)
        }
    }
}

data class ReservaCarta(
    @field:SerializedName("idReservacion")
    val idReservacion: Int,
    @field:SerializedName("platillos")
    val platillos: List<PlatilloReservado>
)

data class PlatilloReservado(
    @field:SerializedName("idPlatillo")
    val idPlatillo: Int,
    @field:SerializedName("cantidad")
    val cantidad: Int
)

interface ReservacionesApiService {
    @POST("crear-reservaciones")
    fun crearReservacion(@Body reservacion: Reservacion): Call<Unit>

    @GET("detallar-reservaciones/{reservacionId}")
    fun getDetalleReservacion(@Path("reservacionId") reservacionId: Int): Call<Unit>

    @GET("listar-reservaciones")
    fun listarReservaciones(): Call<Unit>

    @GET("listar-reservaciones-sedes/{sedeId}")
    fun listarReservacionesPorSede(@Path("sedeId") sedeId: Int): Call<Unit>

    @GET("listar-reservaciones-clientes/{clienteId}")
    fun listarReservacionesPorCliente(@Path("clienteId") clienteId: Int): Call<Unit>

    @PUT("actualizar-reservaciones/{id}")
    fun actualizarReservacion(@Path("id") id: Int): Call<Unit>

    @DELETE("eliminar-reservaciones/{id}")
    fun eliminarReservacion(@Path("id") id: Int): Call<Unit>

    @GET("verificar-reservaciones/{id}")
    fun verificarReservacion(@Path("id") id: Int): Call<Unit>

    @PUT("atender-reservaciones/{id}")
    fun atenderReservacion(@Path("id") id: Int): Call<Unit>

    @GET("listar-sedes")
    fun listarSedes(): Call<Unit>

    @GET("detallar-sedes/{id}")
    fun getDetalleSede(@Path("id") id: Int): Call<Unit>

    companion object {
        private const val BASE_URL = "http://localhost:9000/ne-reservaciones/servicio-al-cliente/v1/"

        fun create(): ReservacionesApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ReservacionesApiService::class.java)
        }
    }
}

data class Reservacion(
    @field:SerializedName("idCliente")
    val idCliente: String,
    @field:SerializedName("idSede")
    val idSede: String,
    @field:SerializedName("numeroMesa")
    val numeroMesa: String,
    @field:SerializedName("numeroSillas")
    val numeroSillas: String,
    @field:SerializedName("horario")
    val horario: String
)

interface UsuariosApiService {
    @GET("listar-usuarios")
    fun listarUsuarios(): Call<Unit>

    @GET("detallar-usuarios/{userId}")
    fun getDetalleUsuario(@Path("userId") userId: Int): Call<Unit>

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

    @POST(value="login")
    fun login(@Body userDTO : userDTO): Call<LoginResponse>

    @POST("registrar-usuarios")
    fun registrarUsuario(@Body usuario: Usuario): Call<Unit>

    companion object {
        private const val BASE_URL = "http://localhost:9000/ne-usuarios/servicio-al-cliente/v1/"

        // Método de fábrica para crear una instancia de la interfaz
        fun create(): UsuariosApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UsuariosApiService::class.java)
        }
    }

}

data class Empleado(
    @field:SerializedName("idSede")
    val idSede: Int,
    @field:SerializedName("estado")
    val estado: String,
    @field:SerializedName("nombres")
    val nombres: String,
    @field:SerializedName("apellidos")
    val apellidos: String,
    @field:SerializedName("telefono")
    val telefono: String
)

data class Cliente(
    @field:SerializedName("dni")
    val dni: String,
    @field:SerializedName("nombres")
    val nombres: String,
    @field:SerializedName("apellidos")
    val apellidos: String,
    @field:SerializedName("telefono")
    val telefono: String
)

data class Credentials(
    @field:SerializedName("correo")
    val correo: String,
    @field:SerializedName("password")
    val password: String
)

data class Usuario(
    @field:SerializedName("idRol")
    val idRol: String,
    @field:SerializedName("idSede")
    val idSede: String,
    @field:SerializedName("nombres")
    val nombres: String,
    @field:SerializedName("apellidos")
    val apellidos: String,
    @field:SerializedName("telefono")
    val telefono: String,
    @field:SerializedName("correo")
    val correo: String,
    @field:SerializedName("password")
    val password: String
)

