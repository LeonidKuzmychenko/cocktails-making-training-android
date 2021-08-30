package lk.game.cocktails.fragments.game.services

import android.widget.TextView
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.adapters.GameItemColorSetter
import lk.game.cocktails.fragments.game.data.GameItemState

class IngredientsStateService(
    private val viewModel: GameViewModel,
    private val colorMapper: GameItemColorSetter,
) {

    fun checkState(ingredientView: TextView, position: Int) {
        viewModel.checkers.value!![position] =
            if (viewModel.checkers.value!![position] == GameItemState.SELECTED) {
                GameItemState.CLEAR
            } else {
                GameItemState.SELECTED
            }
        colorMapper.set(viewModel.checkers.value!![position], ingredientView)
    }

    fun checkResult(ingredientView: TextView, position: Int) {
        val real = viewModel.cocktail.value!!.ingredients[position].consists
        val me = viewModel.checkers.value!![position]

        if (real && me == GameItemState.SELECTED) {
            viewModel.checkers.value!![position] = GameItemState.RIGHT
        } else if (real && me == GameItemState.CLEAR) {
            viewModel.checkers.value!![position] = GameItemState.MISSED
        } else if (!real && me == GameItemState.SELECTED) {
            viewModel.checkers.value!![position] = GameItemState.WRONG
        }

        colorMapper.set(viewModel.checkers.value!![position], ingredientView)
    }
}