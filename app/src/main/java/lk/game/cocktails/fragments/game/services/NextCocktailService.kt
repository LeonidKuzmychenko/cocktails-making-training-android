package lk.game.cocktails.fragments.game.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.retrofit.repository.ApiRepository

class NextCocktailService(
    private val viewModel: GameViewModel,
    private val apiRepository: ApiRepository,
) : NextCocktail {

    private val iSize: Long = 12

    override fun nextCocktail() {
        GlobalScope.launch {
            val cocktail = apiRepository.getCocktail(iSize)
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.checkers.value = mutableListOf()
                for (i in 0..12)
                    viewModel.checkers.value!!.add(GameItemState.CLEAR)
            }
            viewModel.cocktail.postValue(cocktail)
        }
    }
}