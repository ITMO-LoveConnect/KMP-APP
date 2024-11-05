package ru.connect

import android.app.Application
import org.koin.core.component.KoinComponent

class ConnectApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {}
    }
}
