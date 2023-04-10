package com.bobbyecho.muerta.feature.reminder.viewmodel

import com.bobbyecho.muerta.feature.reminder.data.ReminderRepository
import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(
	private val reminderRepository: ReminderRepository
): ViewModel() {
	private val _reminders = MutableStateFlow(emptyList<Reminder>()).cMutableStateFlow()
	val reminderState = combine(_reminders) {
		ReminderState(
			reminders = _reminders.value
		)
	}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ReminderState())

	private val _addReminderState = MutableStateFlow(AddReminderState())
	val addReminderState = _addReminderState.cStateFlow()

	fun onNotesChanged(notes: String) {
		_addReminderState.value = _addReminderState.value.copy(
			description = notes
		)
	}

	fun onTitleChanged(title: String) {
		_addReminderState.value = _addReminderState.value.copy(
			title = title
		)
	}

	fun loadReminders() = viewModelScope.launch {
		reminderRepository.getAllReminder().let {
			_reminders.value = it
		}
	}

	fun insertReminder() = viewModelScope.launch {
		reminderRepository.insertReminder(
			Reminder(
				title = _addReminderState.value.title,
				desc = _addReminderState.value.description,
				isDone = false,
			),
		)
		loadReminders()
	}

	fun toggleReminder(id: Long, isDone: Boolean) = viewModelScope.launch {
		reminderRepository.toggleReminder(
			id = id,
			isDone = isDone
		)
		loadReminders()
	}

	data class ReminderState(
		val reminders: List<Reminder> = emptyList()
	)

	data class AddReminderState(
		val title: String = "",
		val description: String = "",
		val isDone: Boolean = false
	)
}