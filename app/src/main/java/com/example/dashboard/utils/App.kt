package com.example.dashboard.utils

import android.app.Application
import com.example.dashboard.di.component.ApplicationComponent
import com.example.dashboard.di.component.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class App : Application() {

    val TAG = "Application"
    override fun onCreate() {
        super.onCreate()
        initDagger()

        clearCacheFiles()
    }

    lateinit var applicationComponent: ApplicationComponent
    private fun initDagger(){
        applicationComponent= DaggerApplicationComponent.create()
        applicationComponent.inject(this)
        applicationComponent.provideContext(this)
    }
    private fun clearCacheFiles(){
        try {
            val  cachePath = File(cacheDir, FileUtils.CHILD_DIR)
            cachePath.listFiles()?.forEach {
                it.delete()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}