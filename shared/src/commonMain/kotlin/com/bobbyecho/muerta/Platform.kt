package com.bobbyecho.muerta

import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module

interface Platform {
    val name: String
    fun getDatabaseDriver(dbName: String, passphrase: String): SqlDriver
}

expect fun getPlatform(): Platform

expect val platformModule: Module

expect val reminderModule: Module