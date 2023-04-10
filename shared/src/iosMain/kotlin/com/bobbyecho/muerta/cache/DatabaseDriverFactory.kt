package com.bobbyecho.muerta.cache

import co.touchlab.sqliter.DatabaseConfiguration
import com.bobbyecho.muerta.cache.MuertaDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual class DatabaseDriverFactory(
	private val dbName: String,
	private val passphrase: String
) {
	actual fun createDriver(): SqlDriver {
		val config = DatabaseConfiguration(
			name = dbName,
			version = MuertaDb.Schema.version,
			create = { connection ->
				wrapConnection(connection) {
					MuertaDb.Schema.create(it)
				}
			},
			upgrade = { connection, oldVersion, newVersion ->
				wrapConnection(connection) {
					MuertaDb.Schema.migrate(it, oldVersion, newVersion)
				}
			},
			encryptionConfig = DatabaseConfiguration.Encryption(key = passphrase, rekey = passphrase)
		)

		return NativeSqliteDriver(config)
	}
}