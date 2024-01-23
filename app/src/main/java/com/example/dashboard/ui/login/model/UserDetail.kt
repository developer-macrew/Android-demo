package com.example.dashboard.ui.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetail (var name:String,var email:String, var password:String, var address:String):
    Parcelable