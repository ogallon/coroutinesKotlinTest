package co.m.co.gamesource.core

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

/**
 * @author Oscar Gallon on 8/15/18.
 */
abstract class UseCase<out Response, in Params> where Response : Any {

    abstract suspend fun run(params: Params): Either<Throwable, Response>

    operator fun invoke(
            params: Params,
            onResult: (Either<Throwable, Response>) -> Unit = {}
    ) {
        launch(UI) {
            onResult(withContext(CommonPool) {
                run(params)
            })
        }
    }

}
