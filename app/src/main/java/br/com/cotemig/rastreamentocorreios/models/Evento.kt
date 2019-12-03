package br.com.cotemig.rastreamentocorreios.models

data class Evento(
    var data: String = "",
    var hora: String = "",
    var local: String = "",
    var acao: String = "",
    var mensagem: String = "",
    var quando: String = ""
)