package com.example.dashboard.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class StorageHelper(val sharedPreferences: SharedPreferences) {

    fun putInt(key:String, value:Int){
        sharedPreferences.edit()
            .putInt(key,value)
            .apply()
    }
    fun getInt(key: String) = sharedPreferences.getInt(key,-1)

    fun putString(key:String, value:String){
        sharedPreferences.edit()
            .putString(key,value)
            .apply()
    }
    fun getString(key: String) =  sharedPreferences.getString(key,"")

    fun putBoolean(key: String,value: Boolean){
        sharedPreferences.edit()
            .putBoolean(key,value)
            .apply()
    }
    fun getBoolean(key: String) = sharedPreferences.getBoolean(key,false)


    fun <T>putModel(key: String, value :T){
        sharedPreferences.edit()
            .putString(key,Gson().toJson(value))
            .apply()
    }

    inline fun <reified T> getModel(key: String):T?{
        sharedPreferences.getString(key,null)?.let {
            return GsonBuilder().create().fromJson(it,T::class.java)
        }
        return null
    }
    fun <T>putList(key: String, value :ArrayList<T>){
        sharedPreferences.edit()
            .putString(key,Gson().toJson(value))
            .apply()
    }
    inline fun <reified T> getList(key: String):ArrayList<T>{
        sharedPreferences.getString(key,null)?.let {
            return GsonBuilder().create().fromJson(it,object : TypeToken<ArrayList<T>>(){}.type)
        }
        return arrayListOf()
    }

}