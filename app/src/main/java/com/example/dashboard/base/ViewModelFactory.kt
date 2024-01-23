package com.example.dashboard.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.dashboard.repository.MainRepository

object ViewModelFactory {
    inline fun <reified VM : ViewModel> provideFactory(
        clazz: Class<VM>,
        app: Application,
        myRepository: MainRepository,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null,
    ): AbstractSavedStateViewModelFactory =
        object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
//                val cons = clazz.getConstructor(Application::class.java, MainRepository::class.java)
//                return cons.newInstance(app, myRepository) as T

                val cons = clazz.getConstructor()
                return cons.newInstance() as T
            }
        }
}
/**
private val viewModel: MainViewModel by viewModels {
ViewModelFactory.provideFactory(MainViewModel::class.java,
(requireActivity().application as GlobalApplication),
mainRepository,
this
)
}
 */

