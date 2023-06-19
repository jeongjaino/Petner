package com.jaino.petner.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.petner.domain.TimerRepository
import com.jaino.petner.domain.model.Schedule
import com.jaino.petner.presentation.utils.UiEvent
import com.jaino.petner.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TimerRepository
): ViewModel(){

    private val _homeUiEvent : MutableSharedFlow<UiEvent<Unit>> = MutableSharedFlow()
    val homeUiEvent : SharedFlow<UiEvent<Unit>> get() = _homeUiEvent

    private val _homeUiState : MutableStateFlow<UiState<List<Schedule>>> = MutableStateFlow(UiState.Init)
    val homeUiState : StateFlow<UiState<List<Schedule>>> get() = _homeUiState

    private val _weightUiState : MutableStateFlow<String> = MutableStateFlow("")
    val weightUiState : StateFlow<String> get() = _weightUiState

    fun getScheduleList(){
        viewModelScope.launch {
            repository.getFeedTime()
                .onSuccess {
                    _homeUiState.value = UiState.Success(it)
                }
                .onFailure {
                    _homeUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    fun getWeight(){
        viewModelScope.launch {
            repository.getWeight()
                .onSuccess {
                    val weight = Math.abs(it.water)
                    if(weight > 5000){
                        _weightUiState.value = ENOUGH
                    }
                    else if(weight > 1000){
                        _weightUiState.value = NORMAL
                    }
                    else{
                        _weightUiState.value = LACK
                    }
                }
                .onFailure {
                    _homeUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    companion object{
        const val ENOUGH = "충분"
        const val NORMAL = "적당"
        const val LACK = "부족"

    }
}