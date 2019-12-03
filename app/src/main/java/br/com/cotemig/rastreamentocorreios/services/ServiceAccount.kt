package br.com.cotemig.rastreamentocorreios.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import br.com.cotemig.rastreamentocorreios.models.Account

interface ServiceAccount {

    @POST("account/auth")
    fun auth(@Body account: Account): Call<Account>

    @POST("account")
    fun register(@Body account: Account): Call<Account>

}