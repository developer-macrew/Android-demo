package com.example.dashboard.repository

import android.net.Uri
import com.example.dashboard.api.ApiHelper
import com.example.dashboard.api.ApiStates
import com.example.dashboard.api.MainApiHelper
import com.example.dashboard.api.MainApiHelperImpl
import com.example.dashboard.base.BaseApiResponse
import com.example.dashboard.base.BaseResponseModel
import com.example.dashboard.ui.fragments.home.model.AvailableLeadsModel
import com.example.dashboard.ui.login.model.LoginModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper,private val apiHelperMain: MainApiHelper) : BaseApiResponse() {
    suspend fun uploadFile(
        imageUri: Uri?,
        file: File?
    ): Flow<ApiStates<BaseResponseModel<String>>> =
        flow {
            emit(
                if (file != null) {
                    // val request = CreateUserRequest("email", "name", "phone", "password")
//                     safeApiCall { apiHelper.uploadFile(request.toMultiPart("userData")) }
                    safeApiCall { apiHelper.uploadFile(file.toMultiPart("file")) }
                } else {
                    ApiStates.Error("Invalid File")
                }
            )
        }
    suspend fun login(data: HashMap<String, String>): Flow<ApiStates<LoginModel>> =
        flow {
            emit(safeApiCall { apiHelper.login(data) })
        }
    suspend fun availableLeads(data: HashMap<String, String>): Flow<ApiStates<AvailableLeadsModel>> =
        flow {
            emit(safeApiCall { apiHelperMain.availableLeads(data) })
        }
}

data class CreateUserRequest(
    var email: String,
    var name: String,
    var phone: String,
    var password: String,
)