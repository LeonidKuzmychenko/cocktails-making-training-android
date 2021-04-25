package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.R
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

    private val INGREDIENT_SIZE: Long = 14

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
            GameObserver(baseActivity(), binding, viewModel, serverName)
        )
        viewModel.firstRun.observe( //TODO
            viewLifecycleOwner, {
                if (it) {
                    nextCocktail()
                    viewModel.firstRun.value = false
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item_game, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nextCocktail -> nextCocktail()
            R.id.infoCocktail -> {
                Toast.makeText(context, "INFO", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<GameViewModel> {
        return GameViewModel::class.java
    }

    override fun clearSubTitle(): Boolean {
        return false
    }

    private fun nextCocktail(): Boolean {
        GlobalScope.launch {
            val cocktail = getCocktail()
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.checkers.value = mutableListOf()
                for (i in 0..INGREDIENT_SIZE)
                    viewModel.checkers.value!!.add(false)
            }
            viewModel.cocktails.postValue(cocktail)
        }
        return true
    }

    private suspend fun getCocktail(): Cocktail {
        val excludes = sp.getExcludeList().joinToString(",")
        val response = api.getCocktail(excludes, INGREDIENT_SIZE)
        val responseCode = response.code()
        if (responseCode == 215) {
            throw RuntimeException("You are win!!!")
        }
        if (responseCode != 200) {
            throw RuntimeException("ERROR")
        }
        val cocktail = response.body()
        sp.addExclude(cocktail!!.id)
        return cocktail
    }

}