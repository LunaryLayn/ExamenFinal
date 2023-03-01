package net.azarquiel.examenfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenfinal.api.MainRepository
import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.entities.Chiste
import net.azarquiel.examenfinal.entities.Punto
import net.azarquiel.examenfinal.entities.Usuario

class MainViewModel : ViewModel() {


    private var repository: MainRepository = MainRepository()

    fun getAllCategorias(): MutableLiveData<List<Categoria>> {
        val categorias = MutableLiveData<List<Categoria>>()
        GlobalScope.launch(Main) {
            categorias.value = repository.getAllCategorias()
        }
        return categorias
    }

    fun getChistesByCategoria(idcategoria: Long): MutableLiveData<List<Chiste>> {
        val chistes = MutableLiveData<List<Chiste>>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getChistesByCategoria(idcategoria)
        }
        return chistes
    }

    fun getUserByNickAndPass(nick:String, pass:String):MutableLiveData<Usuario> {
        val usuarioresponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioresponse.value = repository.getUserByNickAndPass(nick, pass)
        }
        return usuarioresponse
    }

    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioresponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioresponse.value = repository.saveUsuario(usuario)
        }
        return usuarioresponse
    }

    fun getAvgPuntosByChiste(idchiste: Long): MutableLiveData<Float> {
        val avgpuntos = MutableLiveData<Float>()
        GlobalScope.launch(Main) {
            avgpuntos.value = repository.getAvgPuntosByChiste(idchiste)
        }
        return avgpuntos
    }
    fun saveChiste(chiste: Chiste): MutableLiveData<Chiste> {
        val chisteresponse = MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chisteresponse.value = repository.saveChiste(chiste)
        }
        return chisteresponse
    }

    fun savePuntoByChiste(idchiste: Long, punto: Punto): MutableLiveData<Chiste> {
        var chisteresponse = MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chisteresponse.value = repository.savePuntoByChiste(idchiste, punto)
        }
        return chisteresponse
    }

}