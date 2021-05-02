package lk.game.cocktails.fragments.game.observers

import android.widget.TextView
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.GameFragment
import lk.game.cocktails.fragments.game.adapters.GameItemChangeMapper
import lk.game.cocktails.fragments.game.data.GameItemState

class GameResultAdapterObserver(
    private val fragment: GameFragment,
    private val textView: TextView,
    private val position: Int
) : Observer<Boolean> {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    override fun onChanged(it: Boolean) {
        if (it) {
            textView.setOnClickListener(null)
            result()
        } else {
            textView.setOnClickListener {
                val checkers = fragment.viewModel.checkers
                checkers.value!![position] =
                    if (checkers.value!![position] == GameItemState.SELECTED) {
                        GameItemState.CLEAR
                    } else {
                        GameItemState.SELECTED
                    }
                colorMapper.set(checkers.value!![position], textView)
            }
        }
    }

    private fun result() {
        val real = fragment.viewModel.cocktail.value!!.ingredients[position].consists
        val me = fragment.viewModel.checkers.value!![position]
        val result = getResult(real, me)

        fragment.viewModel.checkers.value!![position] = result
        colorMapper.set(result, textView)
    }

    private fun getResult(real: Boolean, me: GameItemState): GameItemState {
        return if (real && me == GameItemState.SELECTED) {
            GameItemState.RIGHT
        } else if (real && me == GameItemState.CLEAR) {
            GameItemState.MISSED
        } else if (!real && me == GameItemState.SELECTED) {
            GameItemState.WRONG
        } else {
            GameItemState.CLEAR
        }
    }
}