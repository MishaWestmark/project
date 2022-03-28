package com.westmark.unsplash.authentication.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {
    const val AUTH_URI = "https://unsplash.com/oauth/authorize"
    const val TOKEN_URI = "https://unsplash.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "public read_user read_photos write_likes read_collections"
    const val API_URL = "https://api.unsplash.com/"
    const val AUTHORIZATION_HEADER = "Authorization"

    const val CLIENT_ID = "VvTjqiwkBPNtQfQ9mjtJEYIiLA6SCkVgyrP1OmTX1mY"
    const val CLIENT_SECRET = "PYPgnKHu_jexyVyqGvH5lQJpsOAFREBHQf2ghtEXqOI"
    const val CALLBACK_URL = "westmark://westmark.com/callback"

}