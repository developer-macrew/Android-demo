package com.example.dashboard.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionManager {
    fun Context.checkPermissions(permission: Array<String>):Boolean{
        var isGrated=true
        permission.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)isGrated=false
        }
        return isGrated
    }
}