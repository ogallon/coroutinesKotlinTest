package co.m.co.gamesource.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Oscar Gallon on 8/16/18.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAString(): String = "Hola"

}
