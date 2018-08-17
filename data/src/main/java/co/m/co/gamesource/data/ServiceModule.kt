package co.m.co.gamesource.data

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Oscar Gallon on 8/15/18.
 */
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthorizationInterceptor = AuthorizationInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor)
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
            httpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideGameRoute(retrofit: Retrofit): GameRoute = retrofit.create(GameRoute::class.java)

}
