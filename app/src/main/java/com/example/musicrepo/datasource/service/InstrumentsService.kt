package com.example.musicrepo.datasource.service

import com.example.musicrepo.domain.dtos.InstrumentResponse
import retrofit2.Response
import retrofit2.http.GET

interface InstrumentsService {
    @GET("getAllInstruments.php")
    suspend fun getAll() : Response<List<InstrumentResponse>>
}