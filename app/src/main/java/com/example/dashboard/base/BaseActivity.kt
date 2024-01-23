package com.example.dashboard.base

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dashboard.R
import com.example.dashboard.api.ApiStates
import com.example.dashboard.utils.StorageHelper
import javax.inject.Inject


open class BaseActivity : AppCompatActivity() {

    companion object {
        var isNetworkAvailable = false
    }

    @Inject
    lateinit var store: StorageHelper

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var networkRequest: NetworkRequest

    private var connectivityCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityManager.requestNetwork(networkRequest, connectivityCallback)
    }

    @Suppress("DEPRECATION")
    private inline fun <reified T> getParcel(key: String): T? {
        val workshopEventData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, T::class.java)
        } else {
            intent.getParcelableExtra(key)
        }
        return workshopEventData
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show()
        replaceFragment(1, Fragment())
    }
    fun replaceFragment(containerId:Int,fragment: Fragment,bundle: Bundle?=null){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            replace(
                containerId,
                fragment.apply {
                    arguments=bundle
                }
            )
            commit()
        }
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
                showProgress(false)
                successFun(apiState.data)
            }
        }
    }
}