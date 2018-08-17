package co.m.co.gamesource.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * @author Oscar Gallon on 8/16/18.
 */
class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers()
                .values("@")
        val requestBuilder = request.newBuilder()

        if (headers.isNotEmpty()) {
            requestBuilder.addHeader("user-key", "2db6b8ed0b030dd6fb15432607f70878")
                    .removeHeader("@")
        }

        return chain.proceed(requestBuilder.build())
    }

}
