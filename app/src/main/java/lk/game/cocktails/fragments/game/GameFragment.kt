package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.R
import lk.game.cocktails.TAG
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.observers.GameObserver
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Inject


class GameFragment : BaseFragment<FragmentGameBinding, GameViewModel>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var sp: SharedPreferencesService

    @Inject
    @Qualifier(Keys.SERVER_NAME)
    lateinit var serverName: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppComponent).getWebComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cocktails.observe(
            viewLifecycleOwner,
            GameObserver(activity as AppCompatActivity, binding, serverName)
        )
        GlobalScope.launch {
            nextCocktail()
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<GameViewModel> {
        return GameViewModel::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item_game, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nextCocktail -> {
                nextCocktail()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun nextCocktail() {
        GlobalScope.launch {
            val cocktail = getCocktail()
            viewModel.cocktails.postValue(cocktail)
        }
    }

    private suspend fun getCocktail(): Cocktail? {
        val excludes = sp.getExcludeList()
        Log.d(TAG, "Excludes = $excludes")
        val response = api.getCocktail(excludes.joinToString(","), 12)
        val responseCode = response.code()
        if (responseCode == 215) {
            throw RuntimeException("You are win!!!")
        }
        if (responseCode != 200) {
            throw RuntimeException("ERROR")
        }
        val cocktail = response.body()
        sp.addExclude(cocktail!!.id)
        return response.body()
    }
}