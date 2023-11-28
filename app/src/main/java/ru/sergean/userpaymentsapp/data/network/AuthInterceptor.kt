package ru.sergean.userpaymentsapp.data.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.sergean.userpaymentsapp.BuildConfig
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header(APP_KEY_HEADER_NAME, BuildConfig.APP_KEY)
            .header(APP_VERSION_HEADER_NAME, BuildConfig.API_VERSION)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val APP_KEY_HEADER_NAME = "app-key"
        private const val APP_VERSION_HEADER_NAME = "v"
    }
}
