package com.bobbyecho.muerta

import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CounterViewModel: ViewModel() {
	private val _counter = MutableStateFlow(0)
	val state: CStateFlow<Int> = _counter.cStateFlow()

	fun increment() {
		viewModelScope.launch {
			_counter.value = _counter.value + 1
		}
	}
}