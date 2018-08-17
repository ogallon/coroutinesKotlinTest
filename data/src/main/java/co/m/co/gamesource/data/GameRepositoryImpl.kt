package co.m.co.gamesource.data

import co.m.co.gamesource.core.Either
import co.m.co.gamesource.core.Game
import co.m.co.gamesource.core.GameRepository

/**
 * @author Oscar Gallon on 8/15/18.
 */
class GameRepositoryImpl(val gameRoute: GameRoute) : GameRepository {

    override fun searchGames(keyword: String): Either<Throwable, List<Int>> {
        return try {
            val response = gameRoute.searchGame(keyword)
                    .execute()
            if (response.isSuccessful) {
                Either.Value(response.body()?.map {
                    it.id
                } ?: arrayListOf())
            } else {
                Either.Error(ServerException(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Either.Error(e)
        }
    }

    override fun getGameInfo(gameId: Int): Either<Throwable, Game?> {
        return try {
            val response = gameRoute.getGame(gameId)
                    .execute()
            if (response.isSuccessful) {
                if (response.body()?.isNotEmpty() == true) {
                    Either.Value(response.body()!![0])
                } else {
                    Either.Error(ServerException(response.errorBody()?.string()))
                }

            } else {
                Either.Error(ServerException(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Either.Error(e)
        }
    }
}
