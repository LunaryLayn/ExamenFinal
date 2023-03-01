package net.azarquiel.examenfinal.api

import kotlinx.coroutines.Deferred
import net.azarquiel.examenfinal.entities.Chiste
import net.azarquiel.examenfinal.entities.Punto
import net.azarquiel.examenfinal.entities.Respuesta
import net.azarquiel.examenfinal.entities.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ChisteService {
    @GET("categorias")
    fun getAllCategorias(): Deferred<Response<Respuesta>>

    @GET("categoria/{idcategoria}/chistes")
    fun getChistesByCategoria(@Path("idcategoria") idcategoria: Long): Deferred<Response<Respuesta>>

    @GET("usuario")
    fun getUserByNickAndPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String
    ): Deferred<Response<Respuesta>>

    @POST("usuario")
    fun saveUsuario(
        @Body usuario: Usuario
    ): Deferred<Response<Respuesta>>

    @GET("chiste/{idchiste}/avgpuntos")
    fun getAvgPuntosByChiste(@Path("idchiste") idchiste: Long): Deferred<Response<Respuesta>>

    @POST ("chiste")
    fun saveChiste(@Body chiste: Chiste): Deferred<Response<Respuesta>>
    @POST("chiste/{idchiste}/punto")
    fun savePuntoByChiste(@Path("idchiste") idchiste: Long,
                                @Body punto: Punto): Deferred<Response<Respuesta>>
}