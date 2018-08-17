package co.m.co.gamesource

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.m.co.gamesource.core.Game

/**
 * @author Oscar Gallon on 8/16/18.
 */
class GamesAdapter : RecyclerView.Adapter<GameViewHolder>() {

    private val mGames = ArrayList<Game>()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): GameViewHolder {
        return GameViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.game_item, parent, false)
        )
    }

    override fun getItemCount(): Int = mGames.size

    override fun onBindViewHolder(
            holder: GameViewHolder,
            position: Int
    ) {
        holder.bind(mGames[position])
    }

    fun add(games: List<Game>) {
        mGames.addAll(games)
        notifyDataSetChanged()
    }

    fun clear() {
        mGames.clear()
        notifyDataSetChanged()
    }
}
