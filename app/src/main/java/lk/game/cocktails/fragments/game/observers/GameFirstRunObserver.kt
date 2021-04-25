package lk.game.cocktails.fragments.game.observers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.interfaces.GameNextCocktail

class GameFirstRunObserver(
    private val game: GameNextCocktail,
    private val firstRun: MutableLiveData<Boolean>
) : Observer<Boolean> {
    override fun onChanged(it: Boolean) {
        if (it) {
            game.nextCocktail()
            firstRun.value = false
        }
    }
}