package lk.game.cocktails.fragments.game.observers.next.parent

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.retrofit.repository.ApiRepository

abstract class AbstractGameNextCocktail(
    private val cocktail: MutableLiveData<Cocktail>,
    private val checkers: MutableLiveData<MutableList<GameItemState>>,
    private val apiRepository: ApiRepository
) {

    private val INGREDIENT_SIZE = 12L

    protected fun nextCocktail() {
        GlobalScope.launch {
            val cocktail = apiRepository.getCocktail(INGREDIENT_SIZE)
            GlobalScope.launch(Dispatchers.Main) {
                checkers.value = mutableListOf()
                for (i in 0..INGREDIENT_SIZE)
                    checkers.value!!.add(GameItemState.CLEAR)
            }
            this@AbstractGameNextCocktail.cocktail.postValue(cocktail)
        }
    }
}