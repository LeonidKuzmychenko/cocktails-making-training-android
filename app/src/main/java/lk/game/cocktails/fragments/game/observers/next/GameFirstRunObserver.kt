package lk.game.cocktails.fragments.game.observers.next

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.fragments.game.observers.next.parent.AbstractGameNextCocktail
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.retrofit.repository.ApiRepository

class GameFirstRunObserver(
    private val firstRun: MutableLiveData<Boolean>,
    cocktail: MutableLiveData<Cocktail>,
    checkers: MutableLiveData<MutableList<GameItemState>>,
    apiRepository: ApiRepository
) : AbstractGameNextCocktail(cocktail, checkers, apiRepository), Observer<Boolean> {

    override fun onChanged(it: Boolean) {
        if (it) {
            nextCocktail()
            firstRun.value = false
        }
    }
}