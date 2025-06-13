package com.elena_stepkina.mindart.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object NetworkService {
    private const val BASE_URL = "https://image-generator.iaroslav-aleksandrovich-beldin.workers.dev"

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(LoggingInterceptor())
//        .connectTimeout(30, TimeUnit.SECONDS)    // таймаут на подключение
//        .readTimeout(60, TimeUnit.SECONDS)       // таймаут на чтение ответа
//        .writeTimeout(30, TimeUnit.SECONDS)      // таймаут на запись запроса
//        .build()
    private val client = getUnsafeOkHttpClient()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("ImageRepository", "Request method: ${request.method}")
        Log.d("ImageRepository", "Request URL: ${request.url}")
        Log.d("ImageRepository", "Request headers: ${request.headers}")

        val response = chain.proceed(request)

        Log.d("ImageRepository", "Response code: ${response.code}")
        val responseBody = response.peekBody(Long.MAX_VALUE).string()
        Log.d("ImageRepository", "Response body: $responseBody")

        return response
    }
}

fun getUnsafeOkHttpClient(): OkHttpClient {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())
    val sslSocketFactory = sslContext.socketFactory

    return OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()
}