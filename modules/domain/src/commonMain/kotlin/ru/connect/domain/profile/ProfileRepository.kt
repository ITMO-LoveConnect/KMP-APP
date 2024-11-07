package ru.connect.domain.profile

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.UserEditProfile

@Singleton
class ProfileRepository(
    @Provided private val dataSource: ProfileDataSource,
) {

    suspend fun updateProfile(request: UserEditProfile) {
        dataSource.updateProfile(request)
    }
}
