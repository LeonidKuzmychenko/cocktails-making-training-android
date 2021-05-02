package lk.game.cocktails.fragments.game.observers

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.fragments.game.adapters.GameItemChangeMapper
import lk.game.cocktails.fragments.game.data.GameItemState

class GameResultAdapterObserver(
    private val textView: TextView,
    private val checkers: MutableLiveData<MutableList<GameItemState>>,
    private val position: Int
) : Observer<Boolean> {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    override fun onChanged(it: Boolean) {
        if (it) {
            textView.setOnClickListener(null)
        } else {
            textView.setOnClickListener {
                checkers.value!![position] =
                    if (checkers.value!![position] == GameItemState.SELECTED) { //TODO
                        GameItemState.CLEAR
                    } else {
                        GameItemState.SELECTED
                    }
                colorMapper.set(checkers.value!![position], textView)
            }
        }
    }
}