package com.santig.auj_reto.domain

data class Task(
    var title : String = "",
    var description : String = "",
    var id : String = "",
    var completed : Boolean = false
)