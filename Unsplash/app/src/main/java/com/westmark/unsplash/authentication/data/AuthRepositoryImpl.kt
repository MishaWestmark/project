package com.westmark.unsplash.authentication.data

import android.net.Uri
import com.westmark.unsplash.authentication.AuthManager
import com.westmark.unsplash.networking.UnsplashApi
import net.openid.appauth.*
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authManager: AuthManager,
    private val api: UnsplashApi
) : AuthRepository {
    override fun getAuthRequest(): AuthorizationRequest {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse(AuthConfig.AUTH_URI),
            Uri.parse(AuthConfig.TOKEN_URI)
        )
        val redirectUri = Uri.parse(AuthConfig.CALLBACK_URL)
        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        )
            .setScope(AuthConfig.SCOPE)
            .setCodeVerifier(null)
            .build()
    }

    override fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) {
        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            when {
                response != null -> {
                    val accessToken = response.accessToken.orEmpty()
                    val refreshToken = response.refreshToken.orEmpty()
                    authManager.login(accessToken, refreshToken)
                    Timber.d(accessToken)
                    Timber.d("token = $accessToken")
                    onComplete()
                }
                else -> onError()
            }
        }

    }

    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(AuthConfig.CLIENT_SECRET)
    }

    suspend fun logout() {
    }

}