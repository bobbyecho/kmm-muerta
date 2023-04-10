package com.bobbyecho.muerta.feature.reminder.data.datasource.local

import com.bobbyecho.muerta.cache.MuertaDb
import com.bobbyecho.muerta.db.MuertaDbQueries
import com.bobbyecho.muerta.db.Reminder

interface ReminderDaoSq {
	fun getAllReminder(): List<Reminder>
	fun getReminderById(id: Long): Reminder?
	fun insertReminder(reminder: Reminder)
	fun updateReminderById(reminder: Reminder)
	fun deleteReminderById(id: Long)
	fun toggleReminder(id: Long, isDone: Boolean)
}

class ReminderDaoSqImpl(database: MuertaDb): ReminderDaoSq {

	private val queries: MuertaDbQueries = database.muertaDbQueries

	override fun getAllReminder(): List<Reminder> {
		return queries.getAllReminder().executeAsList()
	}

	override fun getReminderById(id: Long): Reminder? {
		return queries.getReminderById(id).executeAsOneOrNull()
	}

	override fun insertReminder(reminder: Reminder) {
		queries.insertReminder(
			// force to null to apply auto increment
			id = null,
			title = reminder.title,
			desc = reminder.desc,
			isDone = reminder.isDone
		)
	}

	override fun updateReminderById(reminder: Reminder) {
		queries.transaction {
			if (reminder.id == null) rollback()

			queries.updateReminder(
				id = reminder.id,
				title = reminder.title,
				desc = reminder.desc,
				isDone = reminder.isDone
			)
		}
	}

	override fun toggleReminder(id: Long, isDone: Boolean) {
		queries.transaction {
			if(id == null) {
				rollback()
			}

			queries.toggleReminder(
				id = id,
				isDone = isDone
			)
		}
	}

	override fun deleteReminderById(id: Long) {
		queries.transaction {
			queries.deleteReminderbyId(id)
		}
	}
}