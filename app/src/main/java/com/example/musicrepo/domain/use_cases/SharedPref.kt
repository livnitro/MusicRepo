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

    fun saveFavList(favList: List<Int>) {
        val editor = sharedPref.edit()
        val listAsString = favList.joinToString(",")
        editor.putString("favList", listAsString)
        editor.apply()
    }

    fun getFavList(): List<Int>{
        val listAsString = sharedPref.getString("favList", null)

        return if (listAsString != null && listAsString.isNotEmpty()) {
            listAsString.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }

    fun addNumberToFavList(number: Int) {
        val listAsString = sharedPref.getString("favList", null)

        val favList = if (!listAsString.isNullOrEmpty()) {
            listAsString.split(",").map { it.toInt() }.toMutableList()
        } else {
            mutableListOf()
        }

        if (!favList.contains(number)) {
            favList.add(number)

            val editor = sharedPref.edit()
            editor.putString("favList", favList.joinToString(","))
            editor.apply()
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