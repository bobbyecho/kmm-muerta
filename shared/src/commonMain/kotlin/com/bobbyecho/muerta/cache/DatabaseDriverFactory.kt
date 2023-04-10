package com.bobbyecho.muerta.cache

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
	fun createDriver(): SqlDriver
}