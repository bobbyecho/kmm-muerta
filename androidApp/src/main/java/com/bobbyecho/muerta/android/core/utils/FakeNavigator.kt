package com.bobbyecho.muerta.android.core.utils

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

object FakeNavigator: DestinationsNavigator {
	override fun clearBackStack(route: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun navigate(
		route: String,
		onlyIfResumed: Boolean,
		navOptions: NavOptions?,
		navigatorExtras: Navigator.Extras?,
	) {
		TODO("Not yet implemented")
	}

	override fun navigate(
		route: String,
		onlyIfResumed: Boolean,
		builder: NavOptionsBuilder.() -> Unit,
	) {
		TODO("Not yet implemented")
	}

	override fun navigateUp(): Boolean {
		TODO("Not yet implemented")
	}

	override fun popBackStack(): Boolean {
		TODO("Not yet implemented")
	}

	override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean): Boolean {
		TODO("Not yet implemented")
	}

}