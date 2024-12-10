package com.example.musicrepo.datasource.service

import com.example.musicrepo.domain.dtos.CatResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatService {
    @GET("getAllCats.php")
    suspend fun getAll() : Response<List<CatResponse>>

}