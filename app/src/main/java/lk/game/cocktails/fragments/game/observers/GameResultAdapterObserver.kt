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
        val checkers = fragment.viewModel.checkers

        val real = fragment.viewModel.cocktail.value!!.ingredients[position].consists
        val me = fragment.viewModel.checkers.value!![position]

        if (real == true && me == GameItemState.SELECTED) {
            checkers.value!![position] = GameItemState.RIGHT
        } else if (real == true && me == GameItemState.CLEAR) {
            checkers.value!![position] = GameItemState.MISSED
        } else if (real == false && me == GameItemState.SELECTED) {
            checkers.value!![position] = GameItemState.WRONG
        }

        colorMapper.set(checkers.value!![position], textView)


    }
}