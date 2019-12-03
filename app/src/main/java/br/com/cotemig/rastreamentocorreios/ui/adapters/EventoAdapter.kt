package br.com.cotemig.rastreamentocorreios.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Evento

class EventoAdapter(var context: Context, var list: List<Evento>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false)

        var data = view.findViewById<TextView>(R.id.data)
        data.text = list[position].data

        var hora = view.findViewById<TextView>(R.id.hora)
        hora.text = list[position].hora

        var local = view.findViewById<TextView>(R.id.local)
        local.text = list[position].local

        var acao = view.findViewById<TextView>(R.id.acao)
        acao.text = list[position].acao

        var mensagem = view.findViewById<TextView>(R.id.mensagem)
        mensagem.text = list[position].mensagem

        var quando = view.findViewById<TextView>(R.id.quando)
        quando.text = list[position].quando

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