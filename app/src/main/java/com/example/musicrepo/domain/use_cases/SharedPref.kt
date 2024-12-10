package com.example.musicrepo.domain.use_cases

import android.content.Context

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
}