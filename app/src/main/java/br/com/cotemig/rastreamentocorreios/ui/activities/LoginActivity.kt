package br.com.cotemig.rastreamentocorreios.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.rastreamentocorreios.R

import android.widget.Toast
import br.com.cotemig.rastreamentocorreios.models.Account
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txt_cadastrar.setOnClickListener{
            telaCadastro()
        }

        txt_esqueci_senha.setOnClickListener{
            telaEsqueciSenha()
        }

        btn_login.setOnClickListener {
            auth()
        }
    }

    fun telaEsqueciSenha() {
        val intent = Intent(this, EsqueciSenhaActivity::class.java)
        startActivity(intent)
    }

    fun telaCadastro(){
        val intent = Intent(this, CadastrarActivity::class.java)
        startActivity(intent)
    }
    fun auth() {

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = edtxt_email.text.toString()
        account.password = edtxt_senha.text.toString()

        var call = s.auth(account)

        call.enqueue(object : Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {
                response?.let {
                    if (it.code() == 200) {
                        Toast.makeText(this@LoginActivity, "Autenticado!!!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuário ou senha inválido", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@LoginActivity, "Ops deu erro", Toast.LENGTH_LONG).show()
            }
        })
    }
}
