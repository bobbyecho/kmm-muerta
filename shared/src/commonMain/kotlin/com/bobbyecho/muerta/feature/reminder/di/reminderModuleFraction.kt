package com.bobbyecho.muerta.feature.reminder.di

import com.bobbyecho.muerta.feature.reminder.data.ReminderRepository
import com.bobbyecho.muerta.feature.reminder.data.ReminderRepositoryImpl
import org.koin.dsl.module

val reminderModuleFraction = module {
	single<ReminderRepository> { ReminderRepositoryImpl(get()) }
}