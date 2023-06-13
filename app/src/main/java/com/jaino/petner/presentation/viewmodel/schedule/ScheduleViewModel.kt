package com.jaino.petner.presentation.viewmodel.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.petner.domain.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val timerRepository: TimerRepository
): ViewModel() {

    private val _feedCountState : MutableStateFlow<Int> = MutableStateFlow(0)
    val feedCountState : StateFlow<Int> get() = _feedCountState

    fun postSchedule(dateTime: String){
        viewModelScope.launch {
            timerRepository.setFeedTime(time = dateTime, count = _feedCountState.value)
                .onFailure {
                    Timber.d(it.message)
                }
        }
    }

    fun plusCount(){
        _feedCountState.value += 1
    }

    fun minusCount(){
        _feedCountState.value -= 1
    }
}