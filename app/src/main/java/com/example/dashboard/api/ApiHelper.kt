package com.example.dashboard.api

import com.example.dashboard.base.BaseResponseModel
import com.example.dashboard.ui.fragments.home.model.AvailableLeadsModel
import com.example.dashboard.ui.login.model.LoginModel
import okhttp3.MultipartBody
import retrofit2.Call

interface ApiHelper {
    fun uploadFile(profileImage: MultipartBody.Part): Call<BaseResponseModel<String>>
    fun login(data: HashMap<String, String>): Call<LoginModel>
}

interface MainApiHelper {
    fun availableLeads(data: HashMap<String, String>): Call<AvailableLeadsModel>
}