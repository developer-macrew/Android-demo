package com.example.dashboard.ui.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.dashboard.R
import com.example.dashboard.base.BaseFragment
import com.example.dashboard.base.ClickListener
import com.example.dashboard.databinding.FragmentSignupBinding
import com.example.dashboard.ui.login.LoginViewModel
import com.example.dashboard.ui.login.model.UserDetail
import com.example.dashboard.utils.Const
import com.example.dashboard.utils.setSpanText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment(), ClickListener {
    private lateinit var binding: FragmentSignupBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
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
        binding.loginTV.setSpanText(
            getString(R.string.already_have_account),
            getString(R.string.login)
        ) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.loginFragment, true)
                .build()
            findNavController().navigate(R.id.loginFragment, Bundle().apply {
                putParcelable(
                    "data", UserDetail(
                        viewModel.name.value!!.trim(),
                        viewModel.email.value!!.trim(),
                        viewModel.password.value!!.trim(),
                        viewModel.address.value!!.trim()
                    )
                )
            }, navOptions)
        }
    }

    private fun saveDetail() {
        val msg = viewModel.validate()
        if (msg.isNotEmpty()) {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.apply {
                store.putModel(
                    Const.Pref.USER_DETAILS,
                    UserDetail(
                        name.value!!.trim(),
                        email.value!!.trim(),
                        password.value!!.trim(),
                        address.value!!.trim()
                    )
                )
            }
            Toast.makeText(
                requireContext(),
                getString(R.string.sign_up_successfully),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(
                SignupFragmentDirections.actionSignupFragmentToLoginFragment(
                    UserDetail(
                        viewModel.name.value!!.trim(),
                        viewModel.email.value!!.trim(),
                        viewModel.password.value!!.trim(),
                        viewModel.address.value!!.trim()
                    )
                )
            )
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.signUpBT -> {
                saveDetail()
            }
        }
    }
}