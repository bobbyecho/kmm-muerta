package com.bobbyecho.muerta.core.di

import com.bobbyecho.muerta.cache.MuertaDb
import com.bobbyecho.muerta.feature.reminder.data.datasource.local.ReminderDaoSq
import com.bobbyecho.muerta.feature.reminder.data.datasource.local.ReminderDaoSqImpl
import com.bobbyecho.muerta.getPlatform
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val databaseModule = module {
	single {
		MuertaDb(
			getPlatform().getDatabaseDriver(
				dbName = "MuertaDb",
				passphrase = "Muerta_Password"
			)
		)
	}

	single<ReminderDaoSq> {
		ReminderDaoSqImpl(get())
	}
}