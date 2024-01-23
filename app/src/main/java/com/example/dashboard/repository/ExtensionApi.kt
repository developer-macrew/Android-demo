package com.example.dashboard.repository

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toMultiPart(key:String)=
    MultipartBody.Part
        .createFormData(
            name = key,
            filename = name,
            body = asRequestBody("multipart/form-data".toMediaTypeOrNull())
        )

fun <T> T.toMultiPart(key: String) = Gson().toJson(this).run{
    MultipartBody.Part.createFormData(key,this) }