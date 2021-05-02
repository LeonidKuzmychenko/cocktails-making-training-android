package lk.game.cocktails.fragments.game

import android.widget.TextView
import androidx.core.content.ContextCompat
import lk.game.cocktails.R

class GameItemChangeMapper {
    fun set(value: GameItemState, text: TextView) {
        when (value) {
            GameItemState.SELECTED -> {
                changeColor(text, R.color.focus, R.color.black)
                return
            }
            GameItemState.MISSED -> {
                changeColor(text, R.color.missed, R.color.black)
                return
            }
            GameItemState.RIGHT -> {
                changeColor(text, R.color.right, R.color.black)
                return
            }
            GameItemState.WRONG -> {
                changeColor(text, R.color.wrong, R.color.black)
                return
            }
            else -> {
                changeColor(text, R.color.app_card, R.color.black)
                return
            }
        }
    }

    private fun changeColor(text: TextView, backgroundColorId: Int, textColorId: Int) {
        val context = text.context
        val backgroundColor = ContextCompat.getColor(context, backgroundColorId)
        text.setBackgroundColor(backgroundColor)
        val textColor = ContextCompat.getColor(context, textColorId)
        text.setTextColor(textColor)
    }
}