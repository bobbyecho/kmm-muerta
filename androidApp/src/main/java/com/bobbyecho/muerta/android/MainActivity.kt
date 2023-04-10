package com.bobbyecho.muerta.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.plusAssign
import com.bobbyecho.muerta.android.features.reminder.presentation.NavGraphs
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalAnimationApi::class,
        ExperimentalMaterialNavigationApi::class,
        ExperimentalMaterialApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val navController = rememberAnimatedNavController()
//                    val bottomSheetState = rememberModalBottomSheetState(
//                        initialValue = ModalBottomSheetValue.Hidden,
//                        skipHalfExpanded = true,
//                        confirmStateChange = { false }
//                    )
//
//                    val bottomSheetNavigator = rememberBottomSheetNavigator()
//                    navController.navigatorProvider += bottomSheetNavigator

//                    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator, ) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
//                            navController = navController,
//                            engine = rememberAnimatedNavHostEngine()
                        )
//                    }
                }
            }
        }
    }
}