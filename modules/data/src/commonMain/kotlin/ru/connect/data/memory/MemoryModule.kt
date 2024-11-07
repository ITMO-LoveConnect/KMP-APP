package ru.connect.data.memory

import com.russhwolf.settings.Settings
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
class MemoryModule {

    @Singleton
    fun provideSettings(): Settings = Settings()
}
