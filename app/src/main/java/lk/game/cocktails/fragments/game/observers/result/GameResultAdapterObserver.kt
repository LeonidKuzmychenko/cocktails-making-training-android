package lk.game.cocktails.fragments.game.observers.result

import android.widget.TextView
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.fragments.game.services.IngredientsStateService

class GameResultAdapterObserver(
    private val ingredientCountService: CountIngredientsService,
    private val ingredientsStateService: IngredientsStateService,
    private val ingredientView: TextView,
    private val position: Int
) : Observer<Boolean> {

    override fun onChanged(it: Boolean) {
        if (it) {
            ingredientView.setOnClickListener(null)
            ingredientsStateService.checkResult(ingredientView, position)
        } else {
            ingredientView.setOnClickListener {
                ingredientsStateService.checkState(ingredientView, position)
                ingredientCountService.checkIngredientsCount()
            }
        }
    }

}