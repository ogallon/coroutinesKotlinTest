package co.m.co.gamesource.core

/**
 * @author Oscar Gallon on 8/15/18.
 */
data class Game(
        val id: Int,
        val name: String,
        val url: String,
        val summary: String,
        val rating: Double,
        val popularity: Double,
        val cover: Cover?
)

data class Cover(
        val url: String,
        val cloudinary_id: String
)
