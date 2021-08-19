package lk.game.cocktails.fragments.game

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.R
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.base.BaseFragment
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailAdapterObserver
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailImageObserver
import lk.game.cocktails.fragments.game.observers.cocktail.GameCocktailTitleObserver
import lk.game.cocktails.fragments.game.observers.next.GameFirstRunObserver
import lk.game.cocktails.fragments.game.observers.next.GameResultObserver
import lk.game.cocktails.retrofit.repository.ApiRepository
import javax.inject.Inject

class GameFragment : BaseFragment<FragmentGameBinding, GameViewModel>() {

    @Inject
    lateinit var apiRepository: ApiRepository

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

    //    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.cocktailImage.setOnTouchListener(OnSwipeListener(requireContext()))
        viewModel.cocktail.observe(viewLifecycleOwner, GameCocktailTitleObserver(baseActivity()))
        viewModel.cocktail.observe(
            viewLifecycleOwner,
            GameCocktailAdapterObserver(
                viewLifecycleOwner,
                viewModel.cocktail,
                viewModel.checkers,
                viewModel.result,
                binding.ingredientsList
            )
        )
        viewModel.cocktail.observe(
            viewLifecycleOwner,
            GameCocktailImageObserver(binding.cocktailImage, serverName)
        )
        viewModel.firstRun.observe(
            viewLifecycleOwner,
            GameFirstRunObserver(
                viewModel.firstRun,
                viewModel.cocktail,
                viewModel.checkers,
                apiRepository
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item_game, menu)
        viewModel.result.observe(
            viewLifecycleOwner,
            GameResultObserver(
                baseActivity(),
                menu,
                viewModel.cocktail,
                viewModel.checkers,
                apiRepository
            )
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nextCocktail -> {
                viewModel.result.value = !viewModel.result.value!!
                if (!viewModel.result.value!!) {
                    val result = nextCocktail()
                    if (result != null) {
                        val name = viewModel.cocktail.value!!.name
                        (baseActivity().applicationContext as AppComponent).logGameAnalyticsEvent(
                            name,
                            result
                        )
                        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            R.id.infoCocktail -> {
                viewCocktailInfo()
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

    private fun viewCocktailInfo() {
        val cocktail = viewModel.cocktail.value!!
        val action = GameFragmentDirections.actionGameFragmentToDialogInfoCocktail(cocktail)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun nextCocktail(): Boolean? {
        val iSize: Long = 12

        var endResult: Boolean? = null
        val ingredients = viewModel.checkers.value

        if (ingredients != null && ingredients.size >= iSize) {
            endResult = true
            for (i in 0..iSize) {
                val result = ingredients[i.toInt()]
                if (result == GameItemState.WRONG || result == GameItemState.MISSED) {
                    endResult = false
                    break
                }
            }
        }

        GlobalScope.launch {
            val cocktail = apiRepository.getCocktail(iSize)
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.checkers.value = mutableListOf()
                for (i in 0..12)
                    viewModel.checkers.value!!.add(GameItemState.CLEAR)
            }
            viewModel.cocktail.postValue(cocktail)
        }

        return endResult
    }
}