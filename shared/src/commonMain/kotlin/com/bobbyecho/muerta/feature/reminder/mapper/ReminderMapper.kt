package com.bobbyecho.muerta.feature.reminder.mapper

import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder
import com.bobbyecho.muerta.db.Reminder as ReminderEntity

fun ReminderEntity.toDomain(): Reminder = Reminder(
	id = this.id,
	title = this.title,
	desc = this.desc ?: "",
	isDone = this.isDone
)

fun Reminder.toEntity(): ReminderEntity = ReminderEntity(
	id = this.id,
	title = this.title,
	desc = this.desc,
	isDone = this.isDone
)