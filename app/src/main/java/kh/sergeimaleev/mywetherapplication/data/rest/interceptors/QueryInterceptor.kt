package kh.sergeimaleev.mywetherapplication.data.rest.interceptors

import kh.sergeimaleev.mywetherapplication.constants.WEATHER_KEY
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor : Interceptor {
    companion object {
        private const val APP_ID = "appid"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder().addQueryParameter(APP_ID, WEATHER_KEY).build()
        val builder = originalRequest.newBuilder().url(url)
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}