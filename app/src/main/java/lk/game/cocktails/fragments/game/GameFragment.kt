package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.R
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.interfaces.GameNextCocktail
import lk.game.cocktails.fragments.game.observers.GameCocktailObserver
import lk.game.cocktails.fragments.game.observers.GameFirstRunObserver
import lk.game.cocktails.retrofit.repository.ApiRepository
import javax.inject.Inject


class GameFragment : BaseFragment<FragmentGameBinding, GameViewModel>(), GameNextCocktail {

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    @Qualifier(Keys.SERVER_NAME)
    lateinit var serverName: String

    private val INGREDIENT_SIZE: Long = 12

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
            GameCocktailObserver(baseActivity(), binding, viewModel.checkers, serverName)
        )
        viewModel.firstRun.observe(
            viewLifecycleOwner,
            GameFirstRunObserver(this, viewModel.firstRun)
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
                val cocktail = viewModel.cocktails.value!!
                val action = GameFragmentDirections.actionGameFragmentToDialogInfoCocktail(cocktail)
                Navigation.findNavController(requireView()).navigate(action)
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

    override fun nextCocktail(): Boolean {
        GlobalScope.launch {
            val cocktail = apiRepository.getCocktail(INGREDIENT_SIZE)
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.checkers.value = mutableListOf()
                for (i in 0..INGREDIENT_SIZE)
                    viewModel.checkers.value!!.add(false)
            }
            viewModel.cocktails.postValue(cocktail)
        }
        return true
    }

}