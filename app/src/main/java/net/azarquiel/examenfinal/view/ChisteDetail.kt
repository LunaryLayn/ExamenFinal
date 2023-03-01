package net.azarquiel.examenfinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.examenfinal.R
import net.azarquiel.examenfinal.entities.*
import net.azarquiel.examenfinal.viewmodel.MainViewModel

class ChisteDetail : AppCompatActivity() {
    private lateinit var ratescorebar: RatingBar
    private lateinit var viewmodel: MainViewModel
    private lateinit var scorebar: RatingBar
    private lateinit var ivchiste: ImageView
    private lateinit var tvcontenido: TextView
    private lateinit var tvcategoria: TextView
    private lateinit var categoria: Categoria
    private var usuario: Usuario? = null
    private lateinit var chiste: Chiste

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chiste_detail)

        chiste = intent.getSerializableExtra("chiste") as Chiste
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        categoria = intent.getSerializableExtra("categoria") as Categoria
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        tvcategoria = findViewById<TextView>(R.id.chistedetcategoria)
        tvcontenido = findViewById<TextView>(R.id.chistedetcontenido)
        ivchiste = findViewById<ImageView>(R.id.chistedetiv)
        scorebar = findViewById<RatingBar>(R.id.chistedetscore)
        ratescorebar = findViewById<RatingBar>(R.id.chistedetgivescore)

        scorebar.setIsIndicator(true)

        tvcategoria.text = categoria.nombre
        tvcontenido.text = chiste.contenido
        Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${categoria.id}.png").into(ivchiste)
        Log.d("Soy una prueba", "hola")

        getScore()

        ratescorebar.setOnRatingBarChangeListener { ratingBar, _, _ ->
            ratingOnClick(ratescorebar.rating)
        }
    }

    private fun ratingOnClick(rating: Float) {
        if(usuario == null) {
            msg("Debes estar logueado para puntuar un chiste")
            ratescorebar.rating=0f
            getScore()
            return
        } else {
            var estrellas = rating.toLong()
            viewmodel.savePuntoByChiste(chiste.id, Punto(0, chiste.id, estrellas)).observe( this, Observer { it ->
                it?.let{
                }
            })
            msg("Puntuacion guardada correctamente")
            getScore()
        }
    }

    private fun getScore() {
        viewmodel.getAvgPuntosByChiste(chiste.id).observe(this, Observer { it ->
            it?.let {
                scorebar.rating = it.toFloat()
            }
        })
    }

    fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}