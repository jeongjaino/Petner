package com.jaino.petner.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.petner.R
import com.jaino.petner.databinding.FragmentHomeBinding
import com.jaino.petner.presentation.adapter.home.HomeAdapter
import com.jaino.petner.presentation.utils.UiEvent
import com.jaino.petner.presentation.utils.UiState
import com.jaino.petner.presentation.utils.showToast
import com.jaino.petner.presentation.viewmodel.home.HomeViewModel
import com.jaino.petner.presentation.widget.WaterProvideDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized"}

    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        initViewModelStates()
        observeData()
        initAdapter()
    }

    private fun initButton(){
        binding.button.setOnClickListener {
            navigateToSchedule()
        }
    }

    private fun initViewModelStates(){
        viewModel.getScheduleList()
        viewModel.getWeight()
    }

    private fun observeData(){
        viewModel.homeUiEvent.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiEvent.Failure -> {
                        requireContext().showToast(it.error.message.toString())
                    }

                    is UiEvent.Success -> { // 펌프 성공
                        showDialog()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.homeUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when(it){
                    is UiState.Init -> {}

                    is UiState.Success -> {
                        adapter.submitList(it.data)
                    }

                    is UiState.Failure -> {}
                }

            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initAdapter(){
        adapter = HomeAdapter()
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showDialog(){
        WaterProvideDialog(
            requireContext()
        ).show()
    }

    private fun navigateToSchedule(){
        findNavController().navigate(
            R.id.action_homeFragment_to_scheduleFragment
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}