package com.example.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.dashboard.databinding.ActivityStartBinding
import com.example.dashboard.utils.Const.Pref.LOGIN_STATUS
import com.example.dashboard.utils.StorageHelper
import com.example.dashboard.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    @Inject
    lateinit var store: StorageHelper
    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val loginStatus = store.getBoolean(LOGIN_STATUS)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this,
                    if (loginStatus) MainActivity::class.java else LoginActivity::class.java
                )
            )
            finish()
        }, 1000)
    }
}