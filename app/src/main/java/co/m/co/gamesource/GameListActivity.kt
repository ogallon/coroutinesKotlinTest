package co.m.co.gamesource

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import co.m.co.gamesource.core.Either
import co.m.co.gamesource.core.Game
import co.m.co.gamesource.core.GetGamesUseCase
import co.m.co.gamesource.data.GameRepositoryImpl
import co.m.co.gamesource.data.GameRoute
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class GameListActivity : AppCompatActivity() {

    @Inject
    lateinit var gameRoute: GameRoute

    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as? GameApplication)?.mComponent?.inject(this)

        rvGames?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = GamesAdapter()
        }

        mProgressDialog = ProgressDialog(this).apply {
            setMessage("Please wait")
            setCancelable(false)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.game_list_menu, menu)
        (menu?.findItem(R.id.search)?.actionView as? SearchView)?.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let { query ->
                        mProgressDialog.show()
                        (rvGames?.adapter as? GamesAdapter)?.clear()

                        GetGamesUseCase(GameRepositoryImpl(gameRoute))(query) {
                            if (it.isValue) {
                                ((rvGames?.adapter as? GamesAdapter)?.add(
                                        (it as Either.Value<List<Game>>).value
                                ))
                            } else {
                                (it as Either.Error<Throwable>).error.printStackTrace()
                            }
                            mProgressDialog.hide()
                        }
                    }

                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })
        }


        return true
    }
}
