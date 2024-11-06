package ru.connect.data.auth

import kotlinx.coroutines.delay
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.AuthDataSource
import ru.connect.domain.auth.models.Faculty
import ru.connect.domain.auth.models.Gender
import ru.connect.domain.auth.models.Session
import ru.connect.domain.auth.models.UserIsuProfile

@Singleton
class AuthApiDataSource : AuthDataSource {

    override suspend fun sendIsuNumber(isuNumber: String) {
        delay(500L)
    }

    override suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session {
        delay(500L)

        return Session(
            token = "token",
            isNewUser = true,
            userIsuProfile = UserIsuProfile(
                name = "John Doe",
                gender = Gender.MALE,
                faculty = Faculty(
                    uuid = "123e4567-e89b-12d3-a456-426614174000", // Sample UUID
                    shortName = "ENG" // Abbreviation for faculty, e.g., Engineering
                ),
                course = 3 // Example course year
            )
        )
    }
}
