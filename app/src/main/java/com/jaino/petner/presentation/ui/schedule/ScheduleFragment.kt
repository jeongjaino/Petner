package com.jaino.petner.presentation.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jaino.petner.R
import com.jaino.petner.databinding.FragmentScheduleBinding
import com.jaino.petner.presentation.viewmodel.schedule.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ScheduleFragment: Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized"}

    private val viewModel : ScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton(){
        binding.postScheduleButton.setOnClickListener {
            viewModel.postSchedule(
                "${binding.timePicker.hour} : ${binding.timePicker.minute}"
            )
        }

        binding.backButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome(){
        findNavController().navigate(
            R.id.action_scheduleFragment_to_homeFragment
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}