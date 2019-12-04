package br.com.cotemig.rastreamentocorreios.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.ListaEventos
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import br.com.cotemig.rastreamentocorreios.ui.adapters.EventoAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_inicio.*
import retrofit2.Call
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var codigo = intent.getStringExtra("codigo")
        id = intent.getStringExtra("id")

        if (codigo.isNullOrEmpty()){
            Toast.makeText(this@DetailsActivity, "Código inválido", Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this@DetailsActivity, "Carregando...", Toast.LENGTH_LONG).show()
            rastrearObjeto(codigo)
        }

        btn_deletar.setOnClickListener{
            deletarObjeto(id, codigo)
        }

    }

    fun deletarObjeto(id: String, codigo: String){
        var s = RetrofitInitializer().serviceObjeto()
        var call = s.deletarObjeto(id, codigo)

        call.enqueue(object : retrofit2.Callback<Void> {

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {

                response?.let {
                    if(it.code() == 200){
                        finish()

                    }
                    else{
                        Toast.makeText(this@DetailsActivity, "resposta da api diferente de 200", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(this@DetailsActivity, "onFailure", Toast.LENGTH_LONG).show()

            }

        })
    }

    fun rastrearObjeto(codigo: String) {

        var s = RetrofitInitializer().serviceObjeto()
        var call = s.rastrearObjeto(codigo)

        call.enqueue(object : retrofit2.Callback<ListaEventos> {

            override fun onResponse(call: Call<ListaEventos>?, response: Response<ListaEventos>?) {

                response?.let {
                    if(it.code() == 200){
                        var lista = it.body().lista
                        lista?.let{lista ->
                            listaEventos.adapter = EventoAdapter(this@DetailsActivity, lista)
                        }

                    }
                    else{
                        Toast.makeText(this@DetailsActivity, "resposta da api diferente de 200", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListaEventos>?, t: Throwable?) {
                Toast.makeText(this@DetailsActivity, "onFailure", Toast.LENGTH_LONG).show()

            }

        })
    }
}
