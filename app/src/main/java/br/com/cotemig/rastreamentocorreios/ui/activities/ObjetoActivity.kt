package br.com.cotemig.rastreamentocorreios.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Account
import br.com.cotemig.rastreamentocorreios.models.Objeto
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_objeto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ObjetoActivity : AppCompatActivity() {

    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)


        id = intent.getStringExtra("id")

        voltar.setOnClickListener{
            finish()
        }

        btn_cadastrar.setOnClickListener{
            cadastrar()
            finish()
        }
    }

    fun cadastrar(){
        var s = RetrofitInitializer().serviceObjeto()

        var id = id
        var objeto = edtxt_senha.text.toString()

        var call = s.cadastrarObjeto(id, objeto)

        call.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                response?.let {
                    if (it.code() == 200) {
                        finish()
                    } else {
                        Toast.makeText(this@ObjetoActivity, "Ocorreu um erro", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(this@ObjetoActivity, "Ops deu erro", Toast.LENGTH_LONG).show()
            }
        })
    }
}
