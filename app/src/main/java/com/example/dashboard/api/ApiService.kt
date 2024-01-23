package com.example.dashboard.api

import com.example.dashboard.base.BaseResponseModel
import com.example.dashboard.ui.fragments.home.model.AvailableLeadsModel
import com.example.dashboard.ui.login.model.LoginModel
import com.example.dashboard.utils.Const.WebUrl
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST(WebUrl.AVTAR)
    fun uploadFile(
        @Part userData: MultipartBody.Part,
        @Part profileImage: MultipartBody.Part
    ):Call<Any>

    @Multipart
    @POST(WebUrl.AVTAR)
    fun uploadFile(@Part profileImage: MultipartBody.Part):Call<BaseResponseModel<String>>
    @FormUrlEncoded
    @POST(WebUrl.LOGIN)
    fun login(
        @FieldMap data: HashMap<String, String>,
    ):Call<LoginModel>

}

interface MainApiService {
    @FormUrlEncoded
    @POST(WebUrl.GET_AVAILABLE_LEADS)
    fun availableLeads(
        @FieldMap data: HashMap<String, String>,
    ):Call<AvailableLeadsModel>
}