package com.westmark.unsplash.authentication.data

import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest

interface AuthRepository {
    fun getAuthRequest(): AuthorizationRequest
    fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: () -> Unit
    )
}