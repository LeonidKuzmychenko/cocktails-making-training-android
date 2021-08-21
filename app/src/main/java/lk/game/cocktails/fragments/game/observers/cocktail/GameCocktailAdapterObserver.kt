package lk.game.cocktails.fragments.game.observers.cocktail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.adapters.GameItemChangeMapper
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailAdapterObserver(
    private val owner: LifecycleOwner,
    private val viewModel: GameViewModel,
    private val binding: FragmentGameBinding,
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        val colorMapper = GameItemChangeMapper()
        val countIngredientsService = CountIngredientsService(viewModel, binding)

        binding.ingredientsList.adapter = GameRecyclerViewAdapter(
            owner,
            viewModel,
            colorMapper,
            countIngredientsService,
            cocktail.ingredients
        )
    }

}