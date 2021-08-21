package lk.game.cocktails.fragments.game.observers.next

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.observers.next.parent.NextCocktail

class GameFirstRunObserver(
    private val firstRun: MutableLiveData<Boolean>,
    private val nextCocktail: NextCocktail
) : Observer<Boolean> {

    override fun onChanged(it: Boolean) {
        if (it) {
            nextCocktail.nextCocktail()
            firstRun.value = false
        }
    }
}