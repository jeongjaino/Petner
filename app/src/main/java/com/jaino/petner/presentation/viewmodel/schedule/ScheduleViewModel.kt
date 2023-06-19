package com.jaino.petner.presentation.viewmodel.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.petner.domain.TimerRepository
import com.jaino.petner.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val timerRepository: TimerRepository
): ViewModel() {

    private val _feedCountState : MutableStateFlow<Int> = MutableStateFlow(1)
    val feedCountState : StateFlow<Int> get() = _feedCountState

    private val _scheduleUiEvent : MutableSharedFlow<UiEvent<Unit>> = MutableSharedFlow()
    val scheduleUiEvent : SharedFlow<UiEvent<Unit>> get() = _scheduleUiEvent

    fun postSchedule(dateTime: String){
        viewModelScope.launch {
            timerRepository.setFeedTime(time = dateTime, count = _feedCountState.value)
                .onSuccess {
                    _scheduleUiEvent.emit(UiEvent.Success(Unit))
                }
                .onFailure {
                    _scheduleUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    fun plusCount(){
        _feedCountState.value += 1
    }

    fun minusCount(){
        if(_feedCountState.value > 1){
            _feedCountState.value -= 1
        }
    }
}