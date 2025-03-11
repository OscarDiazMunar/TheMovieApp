package com.oscar.themovieapp.data.data_remote.injection

import com.oscar.themovieapp.commons.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", Constants.token)
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}