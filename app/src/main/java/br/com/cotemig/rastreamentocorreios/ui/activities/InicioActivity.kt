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
import br.com.cotemig.rastreamentocorreios.models.ListaObjetos
import br.com.cotemig.rastreamentocorreios.ui.adapters.ObjetoAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_objeto.*

class InicioActivity : AppCompatActivity() {

    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        id = intent.getStringExtra("id")

        add_objeto.setOnClickListener{
            val intent = Intent(this, ObjetoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        carregar()
    }

    fun criarFuncaoClique(){
        codigo.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("codigo", codigo.text.toString())
            startActivity(intent)
        }
    }

    fun carregar(){
        var s = RetrofitInitializer().serviceObjeto()
        var call = s.buscarObjetos(id)

        call.enqueue(object : retrofit2.Callback<ListaObjetos> {

            override fun onResponse(call: Call<ListaObjetos>?, response: Response<ListaObjetos>?) {

                response?.let {
                    if(it.code() == 200){
                        var lista = it.body().lista
                        lista?.let{lista ->
                            ListaObjetos.adapter = ObjetoAdapter(this@InicioActivity, lista)
                        }
                        criarFuncaoClique()

                    }
                    else{
                        Toast.makeText(this@InicioActivity, "resposta da api diferente de 200", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListaObjetos>?, t: Throwable?) {
                Toast.makeText(this@InicioActivity, "onFailure", Toast.LENGTH_LONG).show()

            }

        })
    }
}
