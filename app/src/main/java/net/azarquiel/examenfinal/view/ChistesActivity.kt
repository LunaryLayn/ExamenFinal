package net.azarquiel.examenfinal.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout
import net.azarquiel.examenfinal.R
import net.azarquiel.examenfinal.adapters.CategoriaAdapter
import net.azarquiel.examenfinal.adapters.ChisteAdapter
import net.azarquiel.examenfinal.databinding.ActivityChistesBinding
import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.entities.Chiste
import net.azarquiel.examenfinal.entities.Usuario
import net.azarquiel.examenfinal.viewmodel.MainViewModel

class ChistesActivity : AppCompatActivity() {

    private var usuario: Usuario? = null
    private lateinit var chistes: List<Chiste>
    private lateinit var categoria: Categoria
    private lateinit var adapter: ChisteAdapter
    private lateinit var binding: ActivityChistesBinding


    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        categoria = intent.getSerializableExtra("categoria") as Categoria
        usuario = intent.getSerializableExtra("usuario") as Usuario?

        title = "Chistes de ${categoria.nombre}"
        initRV()
        getChistes()
        binding.fab.setOnClickListener { view ->
            if(usuario!=null){ newChiste() } else { msg("Debes estar logueado para añadir un chiste") }
        }
    }

    private fun newChiste() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nuevo chiste")
        val ll = LinearLayout(this)
        ll.setPadding(30, 30, 30, 30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        lp.setMargins(0, 50, 0, 50)

        val textInputLayoutPass = TextInputLayout(this)
        textInputLayoutPass.layoutParams = lp
        val etchiste = EditText(this)
        etchiste.setPadding(0, 80, 0, 80)
        etchiste.textSize = 20.0F
        etchiste.hint = "Redacta tu chiste"
        textInputLayoutPass.addView(etchiste)

        ll.addView(textInputLayoutPass)

        builder.setView(ll)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            saveChiste(etchiste.text.toString())
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
        }
        builder.show()
    }

    private fun saveChiste(s: String) {

        val chi = Chiste(12, categoria.id, s)
        viewmodel.saveChiste(chi).observe(this, Observer { it ->
            it?.let{
                val comentariosanterior = ArrayList(chistes)
                comentariosanterior.add(0, Chiste(12, categoria.id, s))
                chistes = comentariosanterior
                adapter.setChistes(chistes)
                msg("Chiste guardado correctamente")
            }
        })
    }

    private fun msg(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }



    private fun getChistes() {
        viewmodel.getChistesByCategoria(categoria.id).observe(this, Observer { it ->
            it?.let{
                chistes = it
                adapter.setChistes(chistes)
            }
        })
    }

    private fun initRV() {
        adapter = ChisteAdapter(this, R.layout.rowchiste)
        binding.cmchistes.rvchiste.layoutManager = LinearLayoutManager(this)
        binding.cmchistes.rvchiste.adapter = adapter
    }

    fun onClickChiste(v: View) {
        val chiste = v.tag as Chiste
        val intent = Intent(this, ChisteDetail::class.java)
        intent.putExtra("chiste", chiste)
        intent.putExtra("usuario", usuario)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}