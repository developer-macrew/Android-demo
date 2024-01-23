package com.example.dashboard.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dashboard.api.ApiStates
import com.example.dashboard.base.BaseActivity
import com.example.dashboard.repository.MainRepository
import com.example.dashboard.ui.fragments.home.model.AvailableLeadsModel
import com.example.dashboard.utils.StorageHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val app: Application, val store: StorageHelper, val mainRepository: MainRepository) :
    AndroidViewModel(app) {
    var progress = MutableLiveData(false)

    private val _availableLeadsStateFlow = MutableStateFlow<ApiStates<AvailableLeadsModel>>(ApiStates.Loading(false))
    val availableLeadsStateFlow: StateFlow<ApiStates<AvailableLeadsModel>> = _availableLeadsStateFlow

    fun getAvailableLeads(){
        val data = hashMapOf<String,String>()
        data["vendorId"]="1"
        data["vendorLat"]= "28.546596"
        data["vendorLng"]= "77.204411"
        data["vendorRadius"]= "500"

        viewModelScope.launch(Dispatchers.IO) {
            if (BaseActivity.isNetworkAvailable) {
                _availableLeadsStateFlow.emit(ApiStates.Loading())
                mainRepository.availableLeads(data).collect {
                    _availableLeadsStateFlow.emit(it)
                }
            } else _availableLeadsStateFlow.emit(ApiStates.NoInternet())

        }
    }
}