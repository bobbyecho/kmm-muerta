package com.bobbyecho.muerta.android.core.base

import android.app.Application
import com.bobbyecho.muerta.core.base.BaseApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class BaseApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		BaseApplication.initialize {
			androidLogger()
			androidContext(applicationContext)
		}
	}
}