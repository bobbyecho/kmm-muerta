package com.bobbyecho.muerta.core.di

import com.bobbyecho.muerta.SampleUseCase
import org.koin.dsl.module

val sampleModule = module {
	single { SampleUseCase() }
}