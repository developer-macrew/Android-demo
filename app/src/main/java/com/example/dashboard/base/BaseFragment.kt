package com.example.dashboard.base

import android.os.Build
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dashboard.R
import com.example.dashboard.api.ApiStates
import com.example.dashboard.utils.StorageHelper
import javax.inject.Inject

abstract class BaseFragment: Fragment() {
    @Inject
    lateinit var store : StorageHelper

    fun showToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    fun showToast(id: Int) {
        Toast.makeText(requireContext(), getString(id), Toast.LENGTH_SHORT).show()
    }

    @Suppress("DEPRECATION")
    inline fun <reified T> getParcel(key: String): T? {
        val workshopEventData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(key, T::class.java)
        } else {
            arguments?.getParcelable(key)
        }
        return workshopEventData
    }
    open fun showProgress(show: Boolean) {

    }
    open fun <T> handleStates(apiState: ApiStates<T>, successFun : (data: T) -> Unit){
        when(apiState){
            is ApiStates.Loading -> showProgress(apiState.isLoading == true)
            is ApiStates.Error ->  {
                showToast(apiState.error)
                showProgress(false)
            }
            is ApiStates.NoInternet -> {
                showToast(R.string.no_internet)
                showProgress(false)
            }
            is ApiStates.Success -> {
                successFun(apiState.data)
                showProgress(false)
            }
        }
    }

}