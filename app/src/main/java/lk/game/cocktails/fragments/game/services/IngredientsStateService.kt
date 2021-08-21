package lk.game.cocktails.fragments.game.services

import android.widget.TextView
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.adapters.GameItemChangeMapper
import lk.game.cocktails.fragments.game.data.GameItemState

class IngredientsStateService(
    private val viewModel: GameViewModel,
    private val ingredientView: TextView,
    private val colorMapper: GameItemChangeMapper,
    private val position: Int
) {

    fun checkState() {
        viewModel.checkers.value!![position] =
            if (viewModel.checkers.value!![position] == GameItemState.SELECTED) {
                GameItemState.CLEAR
            } else {
                GameItemState.SELECTED
            }
        colorMapper.set(viewModel.checkers.value!![position], ingredientView)
    }

    fun checkResult() {
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