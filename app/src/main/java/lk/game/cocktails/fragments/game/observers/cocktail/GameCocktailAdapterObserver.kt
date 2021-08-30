package lk.game.cocktails.fragments.game.observers.cocktail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.adapters.GameItemColorSetter
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.fragments.game.observers.result.GameResultAdapterObserverFactory
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.fragments.game.services.IngredientsStateService
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailAdapterObserver(
    private val owner: LifecycleOwner,
    private val viewModel: GameViewModel,
    private val countService: CountIngredientsService,
    private val binding: FragmentGameBinding,
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        val colorMapper = GameItemColorSetter()
        val stateService = IngredientsStateService(viewModel, colorMapper)

        val factory = GameResultAdapterObserverFactory(countService, stateService)

        binding.ingredientsList.adapter = GameRecyclerViewAdapter(
            owner,
            viewModel,
            colorMapper,
            factory,
            cocktail.ingredients
        )
    }

}