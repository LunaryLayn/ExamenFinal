package net.azarquiel.examenfinal.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.examenfinal.R
import net.azarquiel.examenfinal.adapters.CategoriaAdapter
import net.azarquiel.examenfinal.adapters.ChisteAdapter
import net.azarquiel.examenfinal.databinding.ActivityChistesBinding
import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.entities.Chiste
import net.azarquiel.examenfinal.viewmodel.MainViewModel

class ChistesActivity : AppCompatActivity() {

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
        title = "Chistes de ${categoria.nombre}"
        initRV()
        getChistes()
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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

}