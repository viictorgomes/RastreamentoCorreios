package br.com.cotemig.rastreamentocorreios.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Evento
import br.com.cotemig.rastreamentocorreios.models.ListaEventos
import br.com.cotemig.rastreamentocorreios.ui.adapters.EventoAdapter
import kotlinx.android.synthetic.main.activity_inicio.*
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        codigo1.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(getString(R.string.codigo1), codigo1.text.toString())
            startActivity(intent)
        }
    }


}
