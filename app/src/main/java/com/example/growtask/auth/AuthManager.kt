package com.example.growtask.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.growtask.utils.AppConstants.CLIENT_ID
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

object AuthManager {

    private const val REDIRECT_URI = "http://example.com/callback/"
    private const val SPOTIFY_PREFS = "spotify_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"
    private const val KEY_EXPIRES_IN = "expires_in"

    private const val SCOPES =
        "user-read-email"

    const val AUTH_REQUEST_CODE: Int = 1212

    private lateinit var sharedPreferences: SharedPreferences
    private var accessToken: String? = null
    private var refreshToken: String? = null
    private var expiresIn: Int = 0

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SPOTIFY_PREFS, Context.MODE_PRIVATE)
        accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        refreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        expiresIn = sharedPreferences.getInt(KEY_EXPIRES_IN, 0)
    }

    fun isLoggedIn(): Boolean {
        return accessToken != null
    }

    fun getAccessToken(): String? {
        return accessToken
    }

    fun getExpiresIn(): Int {
        return expiresIn
    }

    fun login(activity: AppCompatActivity) {
        val builder =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf(SCOPES));
        val request = builder.build()
        AuthorizationClient.openLoginActivity(activity, AUTH_REQUEST_CODE, request)
    }

    fun handleAuthResponse(response: AuthorizationResponse) {
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                accessToken = response.accessToken
                expiresIn = response.expiresIn
                saveTokens()
            }

            AuthorizationResponse.Type.ERROR -> {
                Log.e("SpotifyAuthManager", "Authorization error: ${response.error}")
            }

            else -> {
                Log.d("SpotifyAuthManager", "Authorization result: ${response.type}")
            }
        }
    }

    private fun saveTokens() {
        sharedPreferences.edit().apply {
            putString(KEY_ACCESS_TOKEN, accessToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
            putInt(KEY_EXPIRES_IN, expiresIn)
            apply()
        }
    }

    fun logout() {
        sharedPreferences.edit().apply {
            remove(KEY_ACCESS_TOKEN)
            remove(KEY_REFRESH_TOKEN)
            apply()
        }
        accessToken = null
        refreshToken = null
        expiresIn = 0
    }
}
