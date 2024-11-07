package ru.connect.domain.profile

import ru.connect.domain.auth.models.UserEditProfile

interface ProfileDataSource {

    suspend fun updateProfile(request: UserEditProfile)
}
