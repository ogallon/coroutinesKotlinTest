package co.m.co.gamesource

import android.app.Application
import co.m.co.gamesource.data.ServiceModule
import co.m.co.gamesource.di.AppModule

/**
 * @author Oscar Gallon on 8/16/18.
 */
class GameApplication : Application() {

    val mComponent = DaggerMyComponent.builder()
            .appModule(AppModule())
            .serviceModule(ServiceModule())
            .build()

    override fun onCreate() {
        super.onCreate()

    }
}
