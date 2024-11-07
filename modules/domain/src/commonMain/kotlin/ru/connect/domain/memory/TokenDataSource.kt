package ru.connect.domain.memory

import org.koin.core.annotation.Singleton

@Singleton
class TokenDataSource {

    private var token: String? = null

    fun getToken(): String? = token
    fun setToken(token: String) {
        this.token = token
    }
}
