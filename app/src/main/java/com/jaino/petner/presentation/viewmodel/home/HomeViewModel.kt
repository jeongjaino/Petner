package com.jaino.petner.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.petner.domain.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val timerRepository: TimerRepository
): ViewModel() {

    fun setTime(){
        viewModelScope.launch {
            timerRepository.setFeedTime("set")
                .onFailure {
                    Timber.d(it.message)
                }
        }
    }

}