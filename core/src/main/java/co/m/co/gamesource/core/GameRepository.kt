package co.m.co.gamesource.core

/**
 * @author Oscar Gallon on 8/15/18.
 */
interface GameRepository {

    fun searchGames(keyword: String): Either<Throwable, List<Int>>

    fun getGameInfo(gameId: Int): Either<Throwable, Game?>

}
