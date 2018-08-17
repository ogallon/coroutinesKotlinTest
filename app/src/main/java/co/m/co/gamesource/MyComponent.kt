package co.m.co.gamesource

import co.m.co.gamesource.data.ServiceModule
import co.m.co.gamesource.di.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Oscar Gallon on 8/16/18.
 */
@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface MyComponent {

    fun inject(gameListActivity: GameListActivity)
}
