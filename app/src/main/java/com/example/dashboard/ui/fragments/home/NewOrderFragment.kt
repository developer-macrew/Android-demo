package com.example.dashboard.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dashboard.base.BaseFragment
import com.example.dashboard.databinding.FragmentNewOrderBinding
import com.example.dashboard.ui.MainViewModel
import com.example.dashboard.ui.fragments.home.adapter.ChatAdapterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewOrderFragment : BaseFragment() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding : FragmentNewOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentNewOrderBinding.inflate(inflater,container, false)
        return binding.root
    }
    private val chatAdapter = ChatAdapterAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter=chatAdapter

        initObservers()
        viewModel.getAvailableLeads()
    }
    override fun showProgress(show: Boolean) {
        viewModel.progress.value=show
    }
    private fun initObservers(){

        lifecycleScope.launch {
            viewModel.availableLeadsStateFlow.collect{ apiStates ->
                handleStates(apiStates) { data ->
                    data.data.let {
                        chatAdapter.updateList(it)
                    }
                }
            }
        }
    }
}