package com.example.dashboard.api

sealed class ApiStates<T>{
    data class  NoInternet<T>(var isConnected:Boolean?=false)  :ApiStates<T>()
    data class Success<T>(val data : T):ApiStates<T>()
    data class Error<T>(val error: String):ApiStates<T>()
    data class Loading<T>(var isLoading:Boolean?=true) : ApiStates<T>()
}
