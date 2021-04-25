package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.view.*
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
            GameObserver(baseActivity(), binding, serverName)
        )
        nextCocktail()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item_game, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.nextCocktail) nextCocktail() else false
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<GameViewModel> {
        return GameViewModel::class.java
    }

    private fun nextCocktail(): Boolean {
        GlobalScope.launch {
            viewModel.cocktails.postValue(getCocktail())
        }
        return true
    }

    private suspend fun getCocktail(): Cocktail {
        val excludes = sp.getExcludeList().joinToString(",")
        val response = api.getCocktail(excludes, 12)
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