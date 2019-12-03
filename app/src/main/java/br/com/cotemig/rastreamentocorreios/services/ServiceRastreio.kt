package br.com.cotemig.rastreamentocorreios.services

import br.com.cotemig.rastreamentocorreios.models.Evento
import br.com.cotemig.rastreamentocorreios.models.ListaEventos
import br.com.cotemig.rastreamentocorreios.models.ListaObjetos
import br.com.cotemig.rastreamentocorreios.models.Objeto
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



interface ServiceRastreio {

    @POST("rastrear.php")
    fun rastrearObjeto(@Query("obj") obj: String): Call<ListaEventos>


    @POST("database_get.php")
    fun buscarObjetos(@Query("id") id: String): Call<ListaObjetos>

    @POST("database_add.php")
    fun cadastrarObjeto(@Query("id") id: String, @Query("objeto") objeto: String ): Call<Void>


    @POST("database_delete.php")
    fun deletarObjeto(@Query("id") id: String, @Query("objeto") objeto: String ): Call<Void>
}



