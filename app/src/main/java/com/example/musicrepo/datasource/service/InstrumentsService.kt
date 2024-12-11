package com.example.musicrepo.datasource.service

import com.example.musicrepo.domain.dtos.CatResponse
import com.example.musicrepo.domain.dtos.InstrumentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InstrumentsService {
    @GET("getAllInstruments.php")
    suspend fun getAll() : Response<List<InstrumentResponse>>

    @GET("getAllInstrumentsByCatId.php")
    suspend fun getAllByCatId(@Query("catId") catId: Int) : Response<List<InstrumentResponse>>

    @GET("get5randomInstruments.php")
    suspend fun get5Instruments() : Response<List<InstrumentResponse>>

    @GET("getLast3Instruments.php")
    suspend fun getLast3Instruments() : Response<List<InstrumentResponse>>
}