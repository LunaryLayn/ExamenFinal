package net.azarquiel.examenfinal.api

import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.entities.Chiste
import net.azarquiel.examenfinal.entities.Punto
import net.azarquiel.examenfinal.entities.Usuario

class MainRepository {
    val service = WebAccess.chisteService

    suspend fun getAllCategorias(): List<Categoria> {
        val webResponse = service.getAllCategorias().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.categorias
        }
        return emptyList()
    }

    suspend fun getChistesByCategoria(idcategoria: Long): List<Chiste> {
        val webResponse = service.getChistesByCategoria(idcategoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chistes
        }
        return emptyList()
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun getUserByNickAndPass(nick:String, pass:String): Usuario? {
        val webResponse = service.getUserByNickAndPass(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun getAvgPuntosByChiste(idchiste: Long): Int {
        val webResponse = service.getAvgPuntosByChiste(idchiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avgpuntos
        }
        return 0
    }

    suspend fun saveChiste(chiste: Chiste): Chiste? {
        val webResponse = service.saveChiste(chiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chiste
        }
        return null
    }

    suspend fun savePuntoByChiste(idchiste: Long, punto: Punto): Chiste? {
        var chisteresponse: Chiste? = null
        val webResponse = service.savePuntoByChiste(idchiste, punto).await()
        if (webResponse.isSuccessful) {
            chisteresponse = webResponse.body()!!.chiste
        }
        return chisteresponse
    }
}