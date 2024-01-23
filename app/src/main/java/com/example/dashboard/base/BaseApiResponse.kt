package com.example.dashboard.base

import com.example.dashboard.api.ApiStates
import com.example.dashboard.exception.ErrorHandler.reportError
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

abstract class BaseApiResponse {
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    /**
     * when API return Response(Model)
     */
    private suspend fun <T> safeResApiCall(apiCall: suspend () -> Response<T>): ApiStates<T> {
        try {
            apiCall().apply {
                return when {
                    isSuccessful && body() != null -> {
                        ApiStates.Success(body()!! as T)
                    }

                    else -> ApiStates.Error(message())
                }
            }
        } catch (e: Exception) {
            return ApiStates.Error(e.toString())
        }
    }

    /**
     * when API return Call(Model)
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Call<T>
    ): ApiStates<T> {
        try {
            apiCall().execute().apply {
                // get status code and message
                val pair = JSONObject(gson.toJson(this.body()).toString()).let {
                    when {
                        it.has("httpCode") -> it.optString("httpCode") to it.optString("message")
                        it.has("http_code") -> it.optString("http_code") to it.optString("message")
                        else -> this.code() to reportError(this.code())
                    }
                }
                return when {
                    isSuccessful && body() != null -> {
                        if (pair.first == 200 || pair.first == "200" ) ApiStates.Success(body()!! as T)
                        else ApiStates.Error(pair.second.toString())
                    }
                    else -> ApiStates.Error(message().ifEmpty { pair.second.toString() })
                }
            }
        } catch (e: Exception) {
            return ApiStates.Error(/*"Something Went Wrong"*/e.message.toString())
        }
    }
    inline fun <reified T> safeLocalApiCall(
        apiCall: () -> String
    ): ApiStates<T> {
        try {
            apiCall().let { model->
                return ApiStates.Success(gson.fromJson(model,T::class.java))
            }
        } catch (e: Exception) {
            return ApiStates.Error(e.message.toString())
        }
    }

}