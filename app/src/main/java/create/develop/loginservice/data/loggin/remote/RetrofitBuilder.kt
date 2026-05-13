package create.develop.loginservice.data.loggin.remote

import android.util.Log
import create.develop.loginservice.constant.BASE_URL
import create.develop.loginservice.data.loggin.remote.token.RefreshRequest
import create.develop.loginservice.domain.usecase.SessionManager
import create.develop.loginservice.domain.UserApi
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    /**
     * 1. Interceptor for adding the current Access Token to every request.
     * We use .header() to prevent duplicate headers and skip login/refresh endpoints.
     */
    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val path = originalRequest.url.encodedPath

        // Skip adding Authorization header for login and refresh endpoints
        if (path.contains("auth/login") || path.contains("auth/refresh")) {
            return@Interceptor chain.proceed(originalRequest)
        }

        val requestBuilder = originalRequest.newBuilder()
        SessionManager.accessToken?.let {
            requestBuilder.header("Authorization", "Bearer $it")
        }
        chain.proceed(requestBuilder.build())
    }

    /**
     * 2. Authenticator for handling 401 Unauthorized errors.
     */
    private val authenticator = object : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            // Only retry once to avoid infinite loops
            if (response.count() >= 2) return null

            synchronized(this) {
                val currentToken = SessionManager.accessToken
                val requestToken = response.request.header("Authorization")?.removePrefix("Bearer ")

                // If token changed while we were waiting, retry with the new token immediately
                if (currentToken != requestToken && currentToken != null) {
                    return response.request.newBuilder()
                        .header("Authorization", "Bearer $currentToken")
                        .build()
                }

                val refreshToken = SessionManager.refreshToken ?: return null

                // Separate Retrofit instance for refresh to avoid interceptor recursion
                val refreshApi = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApi::class.java)

                Log.d("Authenticator", "Token expired, calling refresh...")
                val refreshResponse = refreshApi.refreshToken(RefreshRequest(refreshToken)).execute()

                return if (refreshResponse.isSuccessful) {
                    val newTokens = refreshResponse.body()
                    SessionManager.accessToken = newTokens?.accessToken
                    SessionManager.refreshToken = newTokens?.refreshToken

                    Log.d("Authenticator", "Refresh successful")

                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${newTokens?.accessToken}")
                        .build()
                } else {
                    Log.e("Authenticator", "Refresh failed: ${refreshResponse.code()}")
                    SessionManager.accessToken = null
                    SessionManager.refreshToken = null
                    null
                }
            }
        }

        private fun Response.count(): Int {
            var result = 1
            var prior = priorResponse
            while (prior != null) {
                result++
                prior = prior.priorResponse
            }
            return result
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .authenticator(authenticator)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}