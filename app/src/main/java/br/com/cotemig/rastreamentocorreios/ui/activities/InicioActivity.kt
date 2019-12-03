package br.com.cotemig.rastreamentocorreios.ui.activities

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

        rastrearObjeto("OH674471493BR")
    }

    fun rastrearObjeto(codigo: String) {

        var s = RetrofitInitializer().serviceObjeto()
        var call = s.rastrearObjeto(codigo)

        call.enqueue(object : retrofit2.Callback<ListaEventos> {

            override fun onResponse(call: Call<ListaEventos>?, response: Response<ListaEventos>?) {

                response?.let {
                    if(it.code() == 200){
                        listaEventos.adapter = EventoAdapter(this@InicioActivity, it.body().lista)
                        status.text="onResponse - code 200"
                    }
                    else{
                        Toast.makeText(this@InicioActivity, "resposta da api diferente de 200", Toast.LENGTH_LONG).show()
                        status.text="onResponse - code != 200"
                    }
                }
            }

            override fun onFailure(call: Call<ListaEventos>?, t: Throwable?) {
                Toast.makeText(this@InicioActivity, "onFailure", Toast.LENGTH_LONG).show()
                status.text = "onFailure " + t.toString()

            }

        })
    }
}
