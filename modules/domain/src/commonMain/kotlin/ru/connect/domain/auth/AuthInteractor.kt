package ru.connect.domain.auth

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicy
import ru.connect.domain.auth.models.RegisterModel
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
    suspend fun login(isuNumber: String, otpCode: String) {
        authRepository.login(isuNumber, otpCode).also { session ->
            authRepository.saveSession(isuNumber, session.token)
        }
    }

    suspend fun isRegistered(isuNumber: String): Boolean = authRepository.isRegistered(isuNumber)
    suspend fun requestProfilledProfile(isuNumber: String, otpCode: String) = authRepository.requestProfilledProfile(isuNumber, otpCode).also {
        sessionRepository.set(Pair(isuNumber, otpCode))
    }

    suspend fun requestProfilledProfileBase(): UserIsuProfile {
        val pair = sessionRepository.get()
        return authRepository.requestProfilledProfile(pair.first, pair.second)
    }

    suspend fun getTags(): List<TagEntity> = tagsRepository.fetch(Unit, CachePolicy.ALWAYS).orEmpty()

    suspend fun updateProfile(request: UserEditProfile) = profileRepository.updateProfile(request).also {
        val session = sessionRepository.get()
        authRepository.saveSession(session.first, session.second)
    }

    suspend fun isContainsAuthData() = authRepository.isContainsAuthData()

    suspend fun getPairsIsuOtp() = sessionRepository.get()

    suspend fun register(request: RegisterModel) = authRepository.register(request)
}
