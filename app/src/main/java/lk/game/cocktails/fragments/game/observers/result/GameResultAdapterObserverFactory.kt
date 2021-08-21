package lk.game.cocktails.fragments.game.observers.result

import android.widget.TextView
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.fragments.game.services.IngredientsStateService

class GameResultAdapterObserverFactory(
    private val ingredientCountService: CountIngredientsService,
    private val ingredientsStateService: IngredientsStateService,
) {

    fun get(textView: TextView, position: Int): GameResultAdapterObserver {
        return GameResultAdapterObserver(
            ingredientCountService,
            ingredientsStateService,
            textView,
            position
        )
    }

}