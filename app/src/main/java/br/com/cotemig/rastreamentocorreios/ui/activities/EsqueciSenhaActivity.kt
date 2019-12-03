package br.com.cotemig.rastreamentocorreios.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.rastreamentocorreios.R
import br.com.cotemig.rastreamentocorreios.models.Account
import br.com.cotemig.rastreamentocorreios.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_esqueci_senha.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EsqueciSenhaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueci_senha)

        btn_esqueci_senha.setOnClickListener {
            if (edtxt_email.text.toString().trim().isNullOrEmpty()) {
                Toast.makeText(this@EsqueciSenhaActivity, "Preencha o email", Toast.LENGTH_LONG).show()
            } else {
                forgot()
            }

        }

    }
    fun telaLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun forgot(){
        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = edtxt_email.text.toString()

        var call = s.forgot(account)

        call.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                response?.let {
                    telaLogin()
                    Toast.makeText(this@EsqueciSenhaActivity, "Sucesso!", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(this@EsqueciSenhaActivity, "Ops deu erro", Toast.LENGTH_LONG).show()
            }
        })
    }
}
