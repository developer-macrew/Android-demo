package com.example.dashboard.utils

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.activity.result.ActivityResultLauncher

object CommonFunctions {
    fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun openGallery(launcher: ActivityResultLauncher<Intent>, msg: String = "Select Picture") {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        launcher.launch(Intent.createChooser(intent, msg))
    }

    fun picDocument(launcher: ActivityResultLauncher<Intent>, msg: String = "Select File") {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        launcher.launch(Intent.createChooser(intent, msg))
    }
}