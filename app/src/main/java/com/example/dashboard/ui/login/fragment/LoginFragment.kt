package com.example.dashboard.ui.login.fragment

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dashboard.R
import com.example.dashboard.base.BaseFragment
import com.example.dashboard.base.ClickListener
import com.example.dashboard.databinding.FragmentLoginBinding
import com.example.dashboard.repository.MainRepository
import com.example.dashboard.ui.MainActivity
import com.example.dashboard.ui.login.LoginViewModel
import com.example.dashboard.ui.login.model.UserDetail
import com.example.dashboard.utils.Const
import com.example.dashboard.utils.setSpanText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment(), ClickListener {

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        args.userData?.let {
            viewModel.apply {
                name.value = it.name
                email.value = it.email
                password.value = it.password
                address.value = it.address
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.clickListener = this

        store.getModel<UserDetail>(Const.Pref.USER_DETAILS)?.let {
            viewModel.apply {
                name.value = it.name
                email.value = it.email
                password.value = it.password
                address.value = it.address
            }
        }

        binding.signUpTV.setSpanText(
            getString(R.string.dont_have_account),
            getString(R.string.sign_up_)
        ) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }

        initObservers()
    }
    override fun showProgress(show: Boolean) {
        viewModel.progress.value=show
    }
    private fun initObservers(){

        lifecycleScope.launch {
            viewModel.loginStateFlow.collect{ apiStates ->
                handleStates(apiStates) { data ->
//                    showToast("Login Successfully")

                    data.message?.let {
                        showToast(it)
                    }
                    data.data?.let {
//                        store.saveToken(it.token?:"")
                        store.putBoolean(Const.Pref.LOGIN_STATUS, true)
//                        store.putBoolean(Const.Pref.IS_FIRST_TIME,false)
//                        store.setUserData(it.user!!)
//                        store.setProfileData(it.profile!!)
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().finishAffinity()
                    }
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.loginBT -> {
                val msg = viewModel.validateLogin()
                if (msg.isNotEmpty()) {
                    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
                } else viewModel.login()
            }
        }
    }
}