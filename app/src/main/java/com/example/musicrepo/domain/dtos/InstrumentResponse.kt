package com.example.musicrepo.domain.dtos

data class InstrumentResponse(
    val nombre : String,
    val descr : String,
    val marca : String,
    val modelo : String,
    val tipo : String,
    val imagen : String
)
