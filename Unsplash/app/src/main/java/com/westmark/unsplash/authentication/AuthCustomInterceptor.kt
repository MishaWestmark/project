package com.westmark.unsplash.authentication

import com.squareup.moshi.Moshi
import com.westmark.unsplash.authentication.data.AuthConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthCustomInterceptor @Inject constructor(
    private val authManager: AuthManager,
    private val moshi: Moshi
) : Interceptor {

//    private val contentType by lazy { "application/json; charset=utf-8".toMediaType() }

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().newBuilder()
            .addHeader(AuthConfig.AUTHORIZATION_HEADER, "Bearer ${authManager.fetchAccessToken()}")
            .build()
            .let {
                chain.proceed(it)
            }
    }


//        val originalRequest = chain.request()
//
//        synchronized(this) {
//            val accessToken =
//                authManager.fetchAccessToken() ?: return chain.proceed(originalRequest)
//            Timber.d("send request with access token $accessToken")
//
//            val modifiedRequest = originalRequest.newBuilder()
//                .addHeader(AuthConfig.AUTHORIZATION_HEADER, "Bearer  $accessToken")
//                .build()
//
//            var response = chain.proceed(modifiedRequest)
//            if (response.code != UNAUTHORIZED_CODE) return response
//
//            val refreshToken = authManager.fetchRefreshToken() ?: return response
//            response.close()
//
//            Timber.d("send request with refresh token $refreshToken")
//
//            val refreshTokenRequest = createRefreshRequest(refreshToken)
//
//            response = chain.proceed(refreshTokenRequest)
//            if (!response.isSuccessful) {
////                authManager.logout()
//                return response
//            }
//
//            val tokenResponse = moshi.adapter(TokenResponse::class.java)
//                .fromJson(response.body?.string().orEmpty()) ?: return response
//            Timber.d("save response with new token $tokenResponse")
//            authManager.login(tokenResponse.accessToken, tokenResponse.refreshToken)
//
//            response.close()
//        }
//
//        val newRequest = createRequest()
//            .url(originalRequest.url)
//            .header(
//                AuthConfig.AUTHORIZATION_HEADER,
//                "Bearer${authManager.fetchAccessToken()}"
//            )
//            .build()
//        return chain.proceed(newRequest)
//    }
//
//    private fun createRefreshRequest(refreshToken: String): Request {
//        val post = moshi.adapter(RefreshRequest::class.java).toJson(
//            RefreshRequest(
//                clientId = AuthConfig.CLIENT_ID,
//                clientSecret = AuthConfig.CLIENT_SECRET,
//                refreshToken = refreshToken,
//                grantType = "refresh_token"
//            )
//        )
//
//        return createRequest()
//            .post(
//                post.toRequestBody(contentType)
//            )
//            .url(AuthConfig.TOKEN_URI)
//            .build()
//    }
//
//    private fun createRequest() =
//        Request.Builder()
//
//    companion object {
//        private const val UNAUTHORIZED_CODE = 401
//    }
}