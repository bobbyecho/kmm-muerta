package com.bobbyecho.muerta

import com.bobbyecho.muerta.cache.DatabaseDriverFactory
import com.bobbyecho.muerta.feature.reminder.di.reminderModuleFraction
import com.bobbyecho.muerta.feature.reminder.viewmodel.ReminderViewModel
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    override fun getDatabaseDriver(dbName: String, passphrase: String): SqlDriver {
        return DatabaseDriverFactory(
            dbName = dbName,
            passphrase = passphrase,
        ).createDriver()
    }
}

actual fun getPlatform(): Platform = IOSPlatform()

actual val platformModule = module {
    single { CounterViewModel() }
}

actual val reminderModule = module {
    includes(reminderModuleFraction)

    single { ReminderViewModel(get()) }
}

object iOS_ViewModels : KoinComponent {
    fun getCounterViewModel() = get<CounterViewModel>()
    fun getReminderViewModel() = get<ReminderViewModel>()
}