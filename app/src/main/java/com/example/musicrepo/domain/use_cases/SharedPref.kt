package com.example.musicrepo.domain.use_cases

import android.content.Context
import com.example.musicrepo.domain.dtos.InstrumentResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class SharedPref (context: Context){
    private val sharedPref = context.getSharedPreferences("myPref", Context.MODE_PRIVATE)

    fun saveNextId(nextId : Int){
        val editor = sharedPref.edit()
        editor.putInt("nextId", nextId)
        editor.apply()
    }

    fun getNextId() : Int{
        return sharedPref.getInt("nextId", 0)
    }

    fun saveInstrumList(list: Response<List<InstrumentResponse>>) {
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("instrumList", json)
        editor.apply()
    }

    fun getInstrumList(): Response<List<InstrumentResponse>> {
        val json = sharedPref.getString("instrumList", null)
        return if (json != null) {
            val type = object : TypeToken<Response<List<InstrumentResponse>>>() {}.type
            Gson().fromJson(json, type)
        } else {
            Response.success(emptyList())
        }
    }

    fun saveGuitarMode(mode : Int){
        val editor = sharedPref.edit()
        editor.putInt("guitarMode", mode)
        editor.apply()
    }

    fun getGuitarMode() : Int{
        return sharedPref.getInt("guitarMode", 0)
    }

}