package net.azarquiel.examenfinal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.examenfinal.R
import net.azarquiel.examenfinal.entities.Categoria
import net.azarquiel.examenfinal.entities.Chiste

class CategoriaAdapter(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    private var dataList: List<Categoria> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCategorias(categorias: List<Categoria>) {
        this.dataList = categorias
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Categoria){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val categoriatvnombre = itemView.findViewById(R.id.categoriatvnombre) as TextView
            val categoriaiv = itemView.findViewById(R.id.categoriaiv) as ImageView

            categoriatvnombre.text = dataItem.nombre
            Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.id}.png").into(categoriaiv)

            // foto de internet a traves de Picasso
            //  Picasso.get().load("${dataItem.thumbnail.path}/standard_fantastic.${dataItem.thumbnail.extension}").into(ivrowZona)


            itemView.tag = dataItem

        }

    }
}

class ChisteAdapter(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<ChisteAdapter.ViewHolder>() {

    private var dataList: List<Chiste> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setChistes(chistes: List<Chiste>) {
        this.dataList = chistes
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Chiste){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val chistetvnombre = itemView.findViewById(R.id.chistetvnombre) as TextView
            val chisteiv = itemView.findViewById(R.id.chisteiv) as ImageView

            val s = dataItem.contenido
            chistetvnombre.text = s.take(15) + "..."
            Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.idcategoria}.png").into(chisteiv)

            // foto de internet a traves de Picasso
            //  Picasso.get().load("${dataItem.thumbnail.path}/standard_fantastic.${dataItem.thumbnail.extension}").into(ivrowZona)


            itemView.tag = dataItem

        }

    }
}