package br.com.cotemig.rastreamentocorreios.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Objeto

class ObjetoAdapter(var context: Context, var list: List<Objeto>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_objeto, null)

        var data = view.findViewById<TextView>(R.id.codigo)
        data.text = list[position].objeto

        return view
    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}