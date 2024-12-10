package com.example.musicrepo.domain.dtos

data class Instrument(
    val nombre : String,
    val descr : String,
    val marca : String,
    val modelo : String,
    val tipo : String,
    val imagen : String,
    val catId : Int
)
