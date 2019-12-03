package br.com.cotemig.rastreamentocorreios.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Account
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_cadastrar.*
import kotlinx.android.synthetic.main.activity_cadastrar.edtxt_email
import kotlinx.android.synthetic.main.activity_cadastrar.edtxt_senha
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        btn_cadastrar.setOnClickListener{
            cadastrar()
        }
    }

    fun cadastrar(){

        var txtSenha = edtxt_senha.text.toString()
        var txtConfirmarSenha = edtxt_confirmar_senha.text.toString()

        if (txtSenha != txtConfirmarSenha){
            Toast.makeText(this@CadastrarActivity, "As senhas são diferentes.", Toast.LENGTH_LONG).show()
            return
        }

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.name = edtxt_nome.text.toString()
        account.email = edtxt_email.text.toString()
        account.password = edtxt_senha.text.toString()

        var call = s.register(account)

        call.enqueue(object : Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {
                response?.let {
                    if (it.code() == 200) {
                        Toast.makeText(this@CadastrarActivity, "Cadastrado com sucesso!!!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@CadastrarActivity, "Ops deu erro. Tente novamente.  " + it.code().toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@CadastrarActivity, "Ops deu erro", Toast.LENGTH_LONG).show()
            }
        })
    }

}
