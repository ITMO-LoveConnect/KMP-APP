package ru.connect.domain.auth

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicy
import ru.connect.domain.auth.models.Session
import ru.connect.domain.auth.models.UserEditProfile
import ru.connect.domain.auth.models.UserIsuProfile
import ru.connect.domain.profile.ProfileRepository
import ru.connect.domain.session.SessionRepository
import ru.connect.domain.tag.TagRepository
import ru.connect.domain.tag.models.TagEntity

@Singleton
class AuthInteractor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val tagsRepository: TagRepository,
    private val profileRepository: ProfileRepository,
) {
    suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session =
        authRepository.sendOtpCode(isuNumber, otpCode).also { session ->
            sessionRepository.set(session)
        }

    suspend fun getProfile(): UserIsuProfile = sessionRepository.get().userIsuProfile

    suspend fun getTags(): List<TagEntity> = tagsRepository.fetch(Unit, CachePolicy.ALWAYS).orEmpty()

    suspend fun updateProfile(request: UserEditProfile) = profileRepository.updateProfile(request).also {
        val session = sessionRepository.get()
        authRepository.saveSession(session.userId, session.token)
    }

    suspend fun isContainsAuthData() = authRepository.isContainsAuthData()
}
