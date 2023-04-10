package com.bobbyecho.muerta.feature.reminder.data

import com.bobbyecho.muerta.feature.reminder.data.datasource.local.ReminderDaoSq
import com.bobbyecho.muerta.feature.reminder.domain.model.Reminder
import com.bobbyecho.muerta.feature.reminder.mapper.toDomain
import com.bobbyecho.muerta.feature.reminder.mapper.toEntity

interface ReminderRepository {
	suspend fun getAllReminder(): List<Reminder>
	suspend fun getReminderById(id: Long): Reminder?
	suspend fun insertReminder(reminder: Reminder)
	suspend fun updateReminderById(reminder: Reminder)
	suspend fun deleteReminderById(id: Long)
	suspend fun toggleReminder(id: Long, isDone: Boolean)
}

class ReminderRepositoryImpl(
	private val dbSq: ReminderDaoSq
): ReminderRepository {

	override suspend fun getAllReminder(): List<Reminder> {
		return dbSq.getAllReminder().map {
			it.toDomain()
		}
	}

	override suspend fun getReminderById(id: Long): Reminder? {
		return dbSq.getReminderById(id)?.toDomain()
	}

	override suspend fun insertReminder(reminder: Reminder) {
		dbSq.insertReminder(reminder.toEntity())
	}

	override suspend fun updateReminderById(reminder: Reminder) {
		dbSq.updateReminderById(reminder.toEntity())
	}

	override suspend fun toggleReminder(id: Long, isDone: Boolean) {
		dbSq.toggleReminder(id = id, isDone = isDone)
	}

	override suspend fun deleteReminderById(id: Long) {
		TODO("Not yet implemented")
	}
}