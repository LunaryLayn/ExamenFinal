package net.azarquiel.examenfinal.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.examenfinal.R
import net.azarquiel.examenfinal.adapters.CategoriaAdapter
import net.azarquiel.examenfinal.databinding.ActivityMainBinding
import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var categorias: List<Categoria>
    private lateinit var adapter: CategoriaAdapter
    private lateinit var viewmodel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRV()
        getCategorias()
    }

    private fun getCategorias() {
        viewmodel.getAllCategorias().observe(this, Observer { it ->
            it?.let{
                categorias = it
                adapter.setCategorias(categorias)
            }
        })
    }

    private fun initRV() {
        adapter = CategoriaAdapter(this, R.layout.rowcategoria)
        binding.cm.rvzonas.layoutManager = LinearLayoutManager(this)
        binding.cm.rvzonas.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}