package com.example.dashboard.api

import com.example.dashboard.base.BaseResponseModel
import com.example.dashboard.ui.fragments.home.model.AvailableLeadsModel
import com.example.dashboard.ui.login.model.LoginModel
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :ApiHelper{
    override fun uploadFile(profileImage: MultipartBody.Part): Call<BaseResponseModel<String>> =
        apiService.uploadFile(profileImage)

    override fun login(data: HashMap<String, String>): Call<LoginModel> =
        apiService.login(data)

}

class MainApiHelperImpl @Inject constructor(private val apiService: MainApiService) :MainApiHelper{
    override fun availableLeads(data: HashMap<String, String>): Call<AvailableLeadsModel> =
        apiService.availableLeads(data)
}