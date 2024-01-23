package com.example.dashboard.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dashboard.R
import com.example.dashboard.api.ApiStates
import com.example.dashboard.base.BaseActivity
import com.example.dashboard.repository.MainRepository
import com.example.dashboard.ui.login.model.LoginModel
import com.example.dashboard.ui.login.model.UserDetail
import com.example.dashboard.utils.Const
import com.example.dashboard.utils.StorageHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val app: Application, val store: StorageHelper, val mainRepository: MainRepository) :
    AndroidViewModel(app) {

    var progress = MutableLiveData(false)
    var name = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")
    var address = MutableLiveData("")

    fun validateLogin(): String {
        return when {
            email.value?.trim()?.isEmpty() == true -> app.getString(R.string.email_cant_be_empty)
            !Patterns.EMAIL_ADDRESS.matcher(email.value?.trim() ?: "")
                .matches() -> app.getString(R.string.email_not_valid)

            password.value?.isEmpty() == true -> app.getString(R.string.password_cant_be_empty)
            else -> ""
        }
    }
    private val _loginStateFlow = MutableStateFlow<ApiStates<LoginModel>>(ApiStates.Loading(false))
    val loginStateFlow: StateFlow<ApiStates<LoginModel>> = _loginStateFlow

    fun login(){
        val data = hashMapOf<String,String>()
        data["email"]=email.value?.trim()?:""
        data["password"]=password.value?:""

        viewModelScope.launch(Dispatchers.IO) {
            if (BaseActivity.isNetworkAvailable) {
                _loginStateFlow.emit(ApiStates.Loading())
                mainRepository.login(data).collect {
                    _loginStateFlow.emit(it)
                }
            } else _loginStateFlow.emit(ApiStates.NoInternet())

        }
    }

    fun validate(): String {
        return when {
            name.value?.isEmpty() == true -> {
                app.getString(R.string.name_cant_be_empty)
            }

            email.value?.isEmpty() == true -> {
                app.getString(R.string.email_cant_be_empty)
            }

            email.value?.trim()?.matches(Patterns.EMAIL_ADDRESS.toRegex()) == false -> {
                app.getString(R.string.please_enter_valid_email)
            }

            password.value?.isEmpty() == true -> {
                app.getString(R.string.password_cant_be_empty)
            }

            password.value?.trim()?.length!! < 4 -> {
                app.getString(R.string.password_legngth_should_be)
            }

            address.value?.isEmpty() == true -> {
                app.getString(R.string.address_cant_be_empty)
            }

            else -> ""
        }
    }

}