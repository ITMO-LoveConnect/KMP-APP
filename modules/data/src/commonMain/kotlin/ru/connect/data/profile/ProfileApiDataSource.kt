package ru.connect.data.profile

import kotlinx.coroutines.delay
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.UserEditProfile
import ru.connect.domain.profile.ProfileDataSource

@Singleton
class ProfileApiDataSource : ProfileDataSource {

    override suspend fun updateProfile(request: UserEditProfile) {
        delay(500L)
    }
}
