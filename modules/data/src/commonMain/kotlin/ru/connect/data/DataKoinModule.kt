package ru.connect.data

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import ru.connect.data.network.NetworkModule

@ComponentScan
@Module(includes = [NetworkModule::class])
class DataKoinModule
