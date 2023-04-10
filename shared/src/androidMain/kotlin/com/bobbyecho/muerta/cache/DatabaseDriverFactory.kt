package com.bobbyecho.muerta.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

actual class DatabaseDriverFactory(
	private val context: Context,
	private val dbName: String,
	private val passphrase: String
) {
	actual fun createDriver(): SqlDriver {
		val bytePassphrase = SQLiteDatabase.getBytes(passphrase.toCharArray())
		val factory = SupportFactory(bytePassphrase)

		return AndroidSqliteDriver(
			schema =  MuertaDb.Schema,
			context = context,
			name = dbName,
			factory = factory
		)
	}
}