package com.example.dashboard.utils

import android.Manifest
import android.os.Build

object Const {
    const val PREF = "dagger_demo_storage"
    const val CHANNEL_ID = "HRMS_NOTIFICATION"
    const val CHANNEL_NAME = "HRMS Notification"
    const val AUTH = "Bearer"
    const val AUTH_TOKEN = "Basic UzYxMDQ1MzA6OWpATSQ3RFNHSXc1XjRO"
    object WebUrl {
        const val BASE_URL = "https://www.google.com//"
        const val MAIN_BASE_URL = "https://www.google.com//"
        const val AVTAR = "v1/user/avatar"
        const val LOGIN = "api/v1/login"
        const val GET_AVAILABLE_LEADS = "vendorapi/availableLeads"
    }
    object Pref{
        const val AGE = "age"
        const val LOGIN_STATUS = "login_status"
        const val USER_DETAILS = "user_details"
    }
    object Permissions{
        val permissionsStorage : Array<String> =
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) arrayOf(  Manifest.permission.READ_MEDIA_IMAGES)
        else
            if (Build.VERSION.SDK_INT==Build.VERSION_CODES.Q) arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            else arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    }
}