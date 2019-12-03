package br.com.cotemig.rastreamentocorreios.services

import br.com.cotemig.rastreamentocorreios.models.Evento
import br.com.cotemig.rastreamentocorreios.models.ListaEventos
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



interface ServiceRastreio {

    @POST("rastrear.php")
    fun rastrearObjeto(@Query("obj") obj: String): Call<ListaEventos>
}



