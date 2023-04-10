package com.bobbyecho.muerta

import android.content.Context
import com.bobbyecho.muerta.cache.DatabaseDriverFactory
import com.bobbyecho.muerta.feature.reminder.di.reminderModuleFraction
import com.bobbyecho.muerta.feature.reminder.viewmodel.ReminderViewModel
import com.squareup.sqldelight.db.SqlDriver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

class AndroidPlatform : Platform, KoinComponent {
    private val context: Context by inject()

    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    override fun getDatabaseDriver(dbName: String, passphrase: String): SqlDriver {
        return DatabaseDriverFactory(
            context = context,
            passphrase = passphrase,
            dbName = dbName
        ).createDriver()
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual val platformModule = module {
    viewModel {
        CounterViewModel()
    }
}

actual val reminderModule = module {
    includes(reminderModuleFraction)

    viewModel { ReminderViewModel(get()) }
}