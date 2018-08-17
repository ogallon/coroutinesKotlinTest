package co.m.co.gamesource.data

import co.m.co.gamesource.core.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Oscar Gallon on 8/15/18.
 */
interface GameRoute {

    @Headers("@:auth")
    @GET("games/")
    fun searchGame(@Query("search") keyword: String): Call<List<APIGameId>>

    @Headers("@:auth")
    @GET("games/{id}")
    fun getGame(@Path("id") id: Int): Call<List<Game>>
}
