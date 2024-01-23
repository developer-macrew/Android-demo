package com.example.dashboard.di.component

import android.content.Context
import com.example.dashboard.di.module.ApplicationModule
import com.example.dashboard.di.module.NetworkModule
import com.example.dashboard.utils.App
import dagger.Component

@Component(modules = [ApplicationModule::class,NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: App)

    fun provideContext(context: Context)

//    fun inject(service: PushNotificationService)
}