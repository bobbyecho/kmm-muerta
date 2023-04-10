package com.bobbyecho.muerta.feature.reminder.domain.model

data class Reminder(
	val id: Long = 0L,
	val title: String,
	val desc: String = "",
	val isDone: Boolean = false
)