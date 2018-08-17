package co.m.co.gamesource.core

/**
 * @author Oscar Gallon on 8/15/18.
 */
class GetGamesUseCase(private val mGameRepository: GameRepository) : UseCase<List<Game>, String>() {

    override suspend fun run(params: String): Either<Throwable, List<Game>> {
        val gamesIds = mGameRepository.searchGames(params)

        if (gamesIds.isError) {
            return gamesIds as Either.Error<Throwable>
        }

        val gamerIds = (gamesIds as Either.Value<List<Int>>).value

        val games = ArrayList<Game>()

        gamerIds.forEach { id ->
            val game = mGameRepository.getGameInfo(id)
            if (game.isError) {
                return game as Either.Error<Throwable>
            } else {
                games.add((game as Either.Value<Game>).value)
            }
        }

        return Either.Value(games)
    }

}
