package ru.connect

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module
import ru.connect.data.DataKoinModule
import ru.connect.domain.DomainKoinModule
import ru.connect.feature1.Feature1Module
import ru.connect.feature1.Feature2Module
import ru.connect.main.FeatureMainModule
import ru.connect.profile.ProfileFeatureKoinModule
import ru.connect.splash.SplashFeatureKoinModule
import ru.connect.welcome.WelcomeFeatureKoinModule

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            AppModule().module,
            Feature1Module().module,
            Feature2Module().module,
            SplashFeatureKoinModule().module,
            WelcomeFeatureKoinModule().module,
            FeatureMainModule().module,
            DataKoinModule().module,
            DomainKoinModule().module,
            ProfileFeatureKoinModule().module,
        )
    }

@Module
@ComponentScan
class AppModule
