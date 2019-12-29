package kh.sergeimaleev.mywetherapplication.data.rest.client

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kh.sergeimaleev.mywetherapplication.BuildConfig
import kh.sergeimaleev.mywetherapplication.constants.NETWORK_BASE_URL
import kh.sergeimaleev.mywetherapplication.data.rest.interceptors.QueryInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RestClientImpl(
    application: Application
) : RestClient {
    private companion object {
        private const val SOCKET_TIMEOUT_EXCEPTION: Long = 30
        private const val DISK_CACHE_SIZE: Long = 50 * 1024 * 1024 // 50MB
        private const val ENDPOINT = NETWORK_BASE_URL
    }

    private val file = File(application.cacheDir, "http")
    private val cache = Cache(
        file,
        DISK_CACHE_SIZE
    )
    private val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().also {
        if (BuildConfig.DEBUG) {
            it.apply { it.level = HttpLoggingInterceptor.Level.BODY }
        }
    }
    private val okHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .followSslRedirects(true)
        .connectTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
        .readTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
        .writeTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(QueryInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(ENDPOINT)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun <T> createRemoteDataSourceFrom(remoteDataSource: Class<T>): T {
        return retrofit.create(remoteDataSource)
    }
}