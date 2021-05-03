package lk.game.cocktails.fragments.game.observers.result

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.adapters.GameItemChangeMapper
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.retrofit.data.Cocktail

class GameResultAdapterObserver(
    private val cocktail: MutableLiveData<Cocktail>,
    private val checkers: MutableLiveData<MutableList<GameItemState>>,
    private val ingredientView: TextView,
    private val position: Int
) : Observer<Boolean> {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    override fun onChanged(it: Boolean) {
        if (it) {
            ingredientView.setOnClickListener(null)
            result()
        } else {
            ingredientView.setOnClickListener {
                checkers.value!![position] =
                    if (checkers.value!![position] == GameItemState.SELECTED) {
                        GameItemState.CLEAR
                    } else {
                        GameItemState.SELECTED
                    }
                colorMapper.set(checkers.value!![position], ingredientView)
            }
        }
    }

    private fun result() {
        val real = cocktail.value!!.ingredients[position].consists
        val me = checkers.value!![position]

        if (real && me == GameItemState.SELECTED) {
            checkers.value!![position] = GameItemState.RIGHT
        } else if (real && me == GameItemState.CLEAR) {
            checkers.value!![position] = GameItemState.MISSED
        } else if (!real && me == GameItemState.SELECTED) {
            checkers.value!![position] = GameItemState.WRONG
        }

        colorMapper.set(checkers.value!![position], ingredientView)
    }

}