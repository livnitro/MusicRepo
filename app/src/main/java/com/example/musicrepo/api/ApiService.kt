package com.example.musicrepo.api

import com.example.musicrepo.model.Instrument
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getAllInstruments.php")
    fun getAllInstruments(): Call<List<Instrument>>
}
