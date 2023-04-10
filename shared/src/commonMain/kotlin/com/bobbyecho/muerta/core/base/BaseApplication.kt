package com.bobbyecho.muerta.core.base

import com.bobbyecho.muerta.core.di.databaseModule
import com.bobbyecho.muerta.core.di.sampleModule
import com.bobbyecho.muerta.platformModule
import com.bobbyecho.muerta.reminderModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import kotlin.jvm.JvmStatic

object BaseApplication {

	@JvmStatic
	fun initialize(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
		return startKoin {
			appDeclaration()
			modules(getCoreModules())
		}
	}

	// iOS
	fun initalize_iOS() = initialize {  }

	private fun getCoreModules(): List<Module> = listOf(
		sampleModule,
		platformModule,
		databaseModule,
		reminderModule
	)
}