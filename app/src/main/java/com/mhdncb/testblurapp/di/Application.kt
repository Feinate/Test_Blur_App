package com.mhdncb.testblurapp.di

import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application: android.app.Application(){

    override fun onCreate() {
        super.onCreate()
    }

}