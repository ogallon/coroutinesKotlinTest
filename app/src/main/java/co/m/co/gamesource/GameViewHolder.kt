package co.m.co.gamesource

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.m.co.gamesource.core.Game
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

/**
 * @author Oscar Gallon on 8/16/18.
 */
class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val ivICon = itemView.findViewById<ImageView>(R.id.ivICon)
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvSummary = itemView.findViewById<TextView>(R.id.tvSummary)
    val tvScore = itemView.findViewById<TextView>(R.id.tvScore)

    fun bind(game: Game) {
        tvName?.text = game.name
        tvSummary?.text = game.summary
        tvScore?.text = game.rating.toInt().toString()
        game.cover?.cloudinary_id?.let {
            Picasso.get()
                    .load("https://images.igdb.com/igdb/image/upload/t_720p/$it")
                    .into(ivICon)
        }


    }

}
